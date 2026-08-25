package io.github.marksosman.dddcontextmapper

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.completion.PrioritizedLookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ProcessingContext
import io.github.marksosman.dddcontextmapper.psi.CmlBoundedContext
import io.github.marksosman.dddcontextmapper.psi.CmlContextMap
import io.github.marksosman.dddcontextmapper.psi.CmlContextRef
import io.github.marksosman.dddcontextmapper.psi.CmlTypes

private val CONTEXT_MAP_PROPERTIES = listOf("type", "state")

private val CONTEXT_PROPERTIES = listOf(
    "type", "domainVisionStatement", "implementationTechnology",
    "responsibilities", "knowledgeLevel", "businessModel", "evolution"
)

private val DOMAIN_PROPERTIES = listOf("domainVisionStatement")

private val SUBDOMAIN_PROPERTIES = listOf("type", "domainVisionStatement")

private val CONTEXT_MAP_VALUES = mapOf(
    "type" to listOf("SYSTEM_LANDSCAPE", "ORGANIZATIONAL"),
    "state" to listOf("AS_IS", "TO_BE")
)

private val CONTEXT_VALUES = mapOf(
    "type" to listOf("FEATURE", "APPLICATION", "SYSTEM", "TEAM"),
    "knowledgeLevel" to listOf("CONCRETE", "META"),
    "businessModel" to listOf(
        "UNDEFINED", "REVENUE", "ENGAGEMENT", "COMPLIANCE", "COST_REDUCTION"
    ),
    "evolution" to listOf(
        "UNDEFINED", "GENESIS", "CUSTOM_BUILT", "PRODUCT", "COMMODITY"
    )
)

private val SUBDOMAIN_VALUES = mapOf(
    "type" to listOf("CORE_DOMAIN", "SUPPORTING_DOMAIN", "GENERIC_SUBDOMAIN")
)

class CmlCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().withLanguage(CmlLanguage),
            object : CompletionProvider<CompletionParameters>() {
                override fun addCompletions(
                    parameters: CompletionParameters,
                    context: ProcessingContext,
                    result: CompletionResultSet
                ) {
                    val file = parameters.originalFile
                    val offset = parameters.offset
                    val before = file.text.substring(0, offset)
                    val block = enclosingBlock(before)
                    val word = lastWordBefore(before)

                    if (word == "contains" || endsWithContainsList(before)) {
                        val used = containedNames(file, offset)
                        addNames(
                            result,
                            contextNames(file).filterNot { it in used },
                            "BoundedContext",
                            100.0
                        )
                        return
                    }

                    val assigned = assignedProperty(before)
                    if (assigned != null) {
                        val values = valuesFor(block)[assigned]
                        if (values != null) {
                            addNames(result, values, assigned, 100.0)
                            return
                        }
                        if (block == Block.CONTEXT_MAP) {
                            addNames(result, contextNames(file), "BoundedContext", 100.0)
                            return
                        }
                    }

                    if (block == Block.TOP_LEVEL) {
                        val clauses = headerClausesFor(before)
                        if (clauses.isNotEmpty()) {
                            addKeywords(result, clauses, "clause", 100.0)
                            return
                        }
                    }

                    when (block) {
                        Block.BOUNDED_CONTEXT ->
                            addKeywords(result, CONTEXT_PROPERTIES, "property", 100.0)

                        Block.CONTEXT_MAP -> {
                            addKeywords(result, listOf("contains"), "property", 100.0)
                            addNames(result, contextNames(file), "BoundedContext", 90.0)
                            addKeywords(result, CONTEXT_MAP_PROPERTIES, "property", 80.0)
                            addKeywords(result, CmlKeywords.RELATIONSHIPS, "relationship", 50.0)
                        }

                        Block.DOMAIN -> {
                            addKeywords(result, listOf("Subdomain"), "declaration", 100.0)
                            addKeywords(result, DOMAIN_PROPERTIES, "property", 90.0)
                        }

                        Block.SUBDOMAIN ->
                            addKeywords(result, SUBDOMAIN_PROPERTIES, "property", 100.0)

                        Block.TOP_LEVEL ->
                            addKeywords(result, CmlKeywords.DECLARATIONS, "declaration", 100.0)
                    }
                }

                private fun addNames(
                    result: CompletionResultSet,
                    names: List<String>,
                    kind: String,
                    priority: Double
                ) {
                    names.forEach {
                        result.addElement(
                            PrioritizedLookupElement.withPriority(
                                LookupElementBuilder.create(it).withTypeText(kind),
                                priority
                            )
                        )
                    }
                }

                private fun addKeywords(
                    result: CompletionResultSet,
                    words: List<String>,
                    kind: String,
                    priority: Double
                ) {
                    words.forEach {
                        result.addElement(
                            PrioritizedLookupElement.withPriority(
                                LookupElementBuilder.create(it).bold().withTypeText(kind),
                                priority
                            )
                        )
                    }
                }
            }
        )
    }

    private enum class Block { TOP_LEVEL, CONTEXT_MAP, BOUNDED_CONTEXT, DOMAIN, SUBDOMAIN }

    companion object {
        private fun isWordChar(c: Char) = c.isLetterOrDigit() || c == '_'

        private fun valuesFor(block: Block): Map<String, List<String>> = when (block) {
            Block.CONTEXT_MAP -> CONTEXT_MAP_VALUES
            Block.BOUNDED_CONTEXT -> CONTEXT_VALUES
            Block.SUBDOMAIN -> SUBDOMAIN_VALUES
            else -> emptyMap()
        }

        private fun lastWordBefore(before: String): String =
            before.trimEnd { isWordChar(it) }.trimEnd().takeLastWhile { isWordChar(it) }

        private fun assignedProperty(before: String): String? {
            val trimmed = before.trimEnd()
            if (trimmed.endsWith("=")) {
                return trimmed.removeSuffix("=").trimEnd().takeLastWhile { isWordChar(it) }
            }
            if (before.isNotEmpty() && !isWordChar(before.last())) {
                val word = trimmed.takeLastWhile { isWordChar(it) }
                if (word.isNotEmpty()) return word
            }
            return null
        }

        private fun endsWithContainsList(before: String): Boolean {
            val trimmed = before.trimEnd()
            if (!trimmed.endsWith(",")) return false
            val line = trimmed.substringAfterLast('\n')
            return line.trimStart().startsWith("contains")
        }

        private fun headerClausesFor(before: String): List<String> {
            val line = before.substringAfterLast('\n').trim()
            if (line.isEmpty()) return emptyList()
            val head = line.takeWhile { isWordChar(it) }
            return when (head) {
                "BoundedContext" -> listOf("implements", "refines", "realizes")
                "Subdomain" -> listOf("supports")
                else -> emptyList()
            }
        }

        private fun enclosingBlock(before: String): Block {
            var depth = 0
            var i = before.length - 1
            while (i >= 0) {
                when (before[i]) {
                    '}' -> depth++
                    '{' -> {
                        if (depth == 0) return blockAt(before, i)
                        depth--
                    }
                }
                i--
            }
            return Block.TOP_LEVEL
        }

        private fun blockAt(before: String, braceIndex: Int): Block {
            var start = braceIndex - 1
            while (start >= 0 && before[start] != '{' && before[start] != '}') start--
            val header = before.substring(start + 1, braceIndex)
            val keyword = header.split(Regex("[^A-Za-z0-9_]+"))
                .firstOrNull { it in HEADERS }
            return when (keyword) {
                "BoundedContext" -> Block.BOUNDED_CONTEXT
                "ContextMap" -> Block.CONTEXT_MAP
                "Domain" -> Block.DOMAIN
                "Subdomain" -> Block.SUBDOMAIN
                else -> Block.TOP_LEVEL
            }
        }

        private val HEADERS =
            setOf("ContextMap", "BoundedContext", "Domain", "Subdomain")

        private fun contextNames(file: PsiFile): List<String> {
            val local = PsiTreeUtil.findChildrenOfType(file, CmlBoundedContext::class.java)
                .mapNotNull { bc ->
                    bc.node.getChildren(null)
                        .firstOrNull { it.elementType == CmlTypes.IDENTIFIER }
                        ?.text
                }
            val indexed = try {
                CmlContextIndex.allContextNames(file.project)
            } catch (e: Exception) {
                emptyList()
            }
            return (local + indexed).distinct()
        }

        private fun containedNames(file: PsiFile, offset: Int): Set<String> {
            val map = PsiTreeUtil.findChildrenOfType(file, CmlContextMap::class.java)
                .firstOrNull { offset >= it.textRange.startOffset && offset <= it.textRange.endOffset }
                ?: return emptySet()
            return PsiTreeUtil.findChildrenOfType(map, CmlContextRef::class.java)
                .map { it.text }
                .toSet()
        }
    }
}
