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

private val CONTEXT_DECLARATIONS = listOf(
    "Aggregate", "Module", "Application", "Flow",
    "Coordination", "UseCase", "UserStory"
)

private val DOMAIN_PROPERTIES = listOf("domainVisionStatement")

private val SUBDOMAIN_PROPERTIES = listOf("type", "domainVisionStatement")

private val AGGREGATE_PROPERTIES = listOf(
    "owner", "knowledgeLevel", "contentVolatility", "likelihoodForChange",
    "structuralVolatility", "availabilityCriticality", "consistencyCriticality",
    "securityCriticality", "storageSimilarity", "useCases", "userStories"
)

private val AGGREGATE_DECLARATIONS = listOf(
    "Entity", "ValueObject", "DomainEvent", "CommandEvent",
    "Service", "Repository", "Enum", "Trait", "BasicType",
    "DataTransferObject", "Consumer", "Resource"
)

private val MEMBER_KEYWORDS = listOf(
    "aggregateRoot", "def", "extends", "abstract",
    "nullable", "required", "key", "not", "throws"
)

private val PRIMITIVES = listOf(
    "String", "int", "long", "short", "byte", "char", "boolean",
    "double", "float", "void", "Date", "DateTime", "Timestamp",
    "BigDecimal", "BigInteger", "Blob", "Clob", "Object",
    "List", "Set", "Bag", "Map"
)

private val ARROWS = listOf(
    "<->" to "\u2194",
    "->" to "\u2192",
    "<-" to "\u2190"
)

private val ROLES = listOf(
    "OHS" to "Open Host Service",
    "PL" to "Published Language",
    "ACL" to "Anticorruption Layer",
    "CF" to "Conformist",
    "SK" to "Shared Kernel",
    "P" to "Partnership",
    "U" to "Upstream",
    "D" to "Downstream",
    "S" to "Supplier",
    "C" to "Customer"
)

private val NAMEABLE = setOf(
    "ContextMap", "BoundedContext", "Domain", "Subdomain", "Aggregate",
    "Module", "Entity", "ValueObject", "DomainEvent", "CommandEvent",
    "Service", "Repository", "Resource", "Consumer", "Trait", "BasicType",
    "DataTransferObject", "Application", "Flow", "Coordination",
    "UseCase", "UserStory", "Enum"
)

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

private val AGGREGATE_VALUES = mapOf(
    "contentVolatility" to listOf("RARELY", "NORMAL", "OFTEN"),
    "likelihoodForChange" to listOf("RARELY", "NORMAL", "OFTEN"),
    "structuralVolatility" to listOf("RARELY", "NORMAL", "OFTEN"),
    "availabilityCriticality" to listOf("LOW", "NORMAL", "HIGH"),
    "consistencyCriticality" to listOf("LOW", "NORMAL", "HIGH"),
    "securityCriticality" to listOf("LOW", "NORMAL", "HIGH"),
    "storageSimilarity" to listOf("TINY", "NORMAL", "HUGE"),
    "knowledgeLevel" to listOf("CONCRETE", "META")
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
                    val here = parameters.position.node.elementType
                    if (here == CmlTypes.STRING ||
                        here == CmlTypes.LINE_COMMENT ||
                        here == CmlTypes.BLOCK_COMMENT
                    ) return

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

                    val naming = namePositionKeyword(before)
                    if (naming != null) {
                        addNames(result, suggestedNames(naming, file), "suggested name", 10.0)
                        return
                    }

                    if (block == Block.CONTEXT_MAP) {
                        if (insideRoleBrackets(before)) {
                            addPairs(result, ROLES, "role", 100.0)
                            return
                        }
                        if (before.trimEnd().endsWith("]")) {
                            addPairs(result, ARROWS, "arrow", 100.0)
                            return
                        }
                    }

                    val assigned = assignedProperty(before)
                    if (assigned != null) {
                        val values = valuesFor(block)[assigned]
                        if (values != null) {
                            addNames(result, values, assigned, 100.0)
                            return
                        }
                        if (block == Block.CONTEXT_MAP && assigned !in CONTEXT_MAP_PROPERTIES) {
                            addPairs(result, ARROWS, "arrow", 100.0)
                            addNames(result, contextNames(file), "BoundedContext", 90.0)
                            return
                        }
                        if (assigned in propertiesFor(block)) return
                    }

                    val clauses = headerClausesFor(before)
                    if (clauses.isNotEmpty()) {
                        addKeywords(result, clauses, "clause", 100.0)
                        return
                    }

                    when (block) {
                        Block.BOUNDED_CONTEXT -> {
                            addKeywords(result, CONTEXT_DECLARATIONS, "declaration", 100.0)
                            addKeywords(result, CONTEXT_PROPERTIES, "property", 90.0)
                        }

                        Block.CONTEXT_MAP -> {
                            addKeywords(result, listOf("contains"), "property", 100.0)
                            addNames(result, contextNames(file), "BoundedContext", 90.0)
                            addKeywords(result, CONTEXT_MAP_PROPERTIES, "property", 80.0)
                            addPairs(result, ARROWS, "arrow", 70.0)
                        }

                        Block.DOMAIN -> {
                            addKeywords(result, listOf("Subdomain"), "declaration", 100.0)
                            addKeywords(result, DOMAIN_PROPERTIES, "property", 90.0)
                        }

                        Block.SUBDOMAIN ->
                            addKeywords(result, SUBDOMAIN_PROPERTIES, "property", 100.0)

                        Block.AGGREGATE -> {
                            addKeywords(result, AGGREGATE_DECLARATIONS, "declaration", 100.0)
                            addKeywords(result, AGGREGATE_PROPERTIES, "property", 90.0)
                        }

                        Block.MEMBER -> {
                            addKeywords(result, PRIMITIVES, "type", 100.0)
                            addKeywords(result, MEMBER_KEYWORDS, "modifier", 90.0)
                            addKeywords(result, AGGREGATE_DECLARATIONS, "declaration", 60.0)
                        }

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

                private fun addPairs(
                    result: CompletionResultSet,
                    pairs: List<Pair<String, String>>,
                    kind: String,
                    priority: Double
                ) {
                    pairs.forEach { (inserted, shown) ->
                        result.addElement(
                            PrioritizedLookupElement.withPriority(
                                LookupElementBuilder.create(inserted)
                                    .withPresentableText(shown)
                                    .withLookupString(shown)
                                    .withTailText("  $inserted", true)
                                    .bold()
                                    .withTypeText(kind),
                                priority
                            )
                        )
                    }
                }
            }
        )
    }

    private enum class Block {
        TOP_LEVEL, CONTEXT_MAP, BOUNDED_CONTEXT, DOMAIN, SUBDOMAIN, AGGREGATE, MEMBER
    }

    companion object {
        private fun isWordChar(c: Char) = c.isLetterOrDigit() || c == '_'

        private fun valuesFor(block: Block): Map<String, List<String>> = when (block) {
            Block.CONTEXT_MAP -> CONTEXT_MAP_VALUES
            Block.BOUNDED_CONTEXT -> CONTEXT_VALUES
            Block.SUBDOMAIN -> SUBDOMAIN_VALUES
            Block.AGGREGATE -> AGGREGATE_VALUES
            else -> emptyMap()
        }

        private fun propertiesFor(block: Block): List<String> = when (block) {
            Block.CONTEXT_MAP -> CONTEXT_MAP_PROPERTIES
            Block.BOUNDED_CONTEXT -> CONTEXT_PROPERTIES
            Block.DOMAIN -> DOMAIN_PROPERTIES
            Block.SUBDOMAIN -> SUBDOMAIN_PROPERTIES
            Block.AGGREGATE -> AGGREGATE_PROPERTIES
            else -> emptyList()
        }

        private fun lineWords(before: String): List<String> =
            before.substringAfterLast('\n').trim()
                .split(Regex("\\s+"))
                .filter { it.isNotEmpty() }

        private fun namePositionKeyword(before: String): String? {
            val line = before.substringAfterLast('\n')
            if (line.isBlank()) return null
            val words = lineWords(before)
            val trailingSpace = line.last().isWhitespace()
            return when {
                words.size == 1 && trailingSpace && words[0] in NAMEABLE -> words[0]
                words.size == 2 && !trailingSpace && words[0] in NAMEABLE -> words[0]
                else -> null
            }
        }

        private fun baseName(file: PsiFile): String =
            file.name.substringBeforeLast('.')
                .split(Regex("[^A-Za-z0-9]+"))
                .filter { it.isNotEmpty() }
                .joinToString("") { part -> part.replaceFirstChar { it.uppercaseChar() } }

        private fun suggestedNames(keyword: String, file: PsiFile): List<String> {
            val base = baseName(file).takeIf { it.isNotEmpty() }
            val generic = "My" + keyword.replaceFirstChar { it.uppercaseChar() }
            val candidates = when (keyword) {
                "ContextMap" -> listOfNotNull(
                    base?.let { "${it}ContextMap" },
                    base?.let { "${it}Map" },
                    "MyContextMap"
                )
                "BoundedContext" -> listOfNotNull(
                    base?.let { "${it}Context" },
                    "MyBoundedContext"
                )
                "Domain" -> listOfNotNull(base?.let { "${it}Domain" }, "MyDomain")
                else -> listOf(generic)
            }
            val existing = contextNames(file).toSet()
            return candidates.distinct().filterNot { it in existing }
        }

        private fun insideRoleBrackets(before: String): Boolean {
            val line = before.substringAfterLast('\n')
            return line.lastIndexOf('[') > line.lastIndexOf(']')
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
            val words = lineWords(before)
            if (words.size < 2) return emptyList()
            val candidates = when (words[0]) {
                "BoundedContext" -> listOf("implements", "refines", "realizes")
                "Subdomain" -> listOf("supports")
                else -> return emptyList()
            }
            return candidates.filterNot { it in words }
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
                .firstOrNull { it in HEADERS || it in MEMBER_HEADERS }
            return when {
                keyword == "BoundedContext" -> Block.BOUNDED_CONTEXT
                keyword == "ContextMap" -> Block.CONTEXT_MAP
                keyword == "Domain" -> Block.DOMAIN
                keyword == "Subdomain" -> Block.SUBDOMAIN
                keyword == "Aggregate" -> Block.AGGREGATE
                keyword in MEMBER_HEADERS -> Block.MEMBER
                else -> Block.TOP_LEVEL
            }
        }

        private val HEADERS =
            setOf("ContextMap", "BoundedContext", "Domain", "Subdomain", "Aggregate")

        private val MEMBER_HEADERS = setOf(
            "Entity", "ValueObject", "DomainEvent", "CommandEvent", "Event",
            "Command", "Service", "Repository", "Resource", "Consumer",
            "Trait", "BasicType", "DataTransferObject", "Module",
            "Application", "Flow", "Coordination", "UseCase", "UserStory",
            "Enum", "enum"
        )

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
