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

private val CONTEXT_PROPERTIES = listOf(
    "type", "domainVisionStatement", "implementationTechnology",
    "responsibilities", "knowledgeLevel", "implements", "realizes", "refines"
)

private val PROPERTY_VALUES = mapOf(
    "type" to listOf("FEATURE", "APPLICATION", "SYSTEM", "TEAM"),
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
                    val file = parameters.originalFile
                    val offset = parameters.offset
                    val before = file.text.substring(0, offset)
                    val word = lastWordBefore(before)

                    if (word == "contains") {
                        val used = containedNames(file, offset)
                        addNames(
                            result,
                            contextNames(file).filterNot { it in used },
                            "BoundedContext",
                            100.0
                        )
                        return
                    }

                    if (before.trimEnd().endsWith("=")) {
                        val prop = propertyNameBefore(before)
                        PROPERTY_VALUES[prop]?.let {
                            addNames(result, it, prop, 100.0)
                            return
                        }
                        addNames(result, contextNames(file), "BoundedContext", 100.0)
                        return
                    }

                    when (enclosingBlock(file, offset)) {
                        Block.BOUNDED_CONTEXT -> {
                            addKeywords(result, CONTEXT_PROPERTIES, "property", 100.0)
                        }
                        Block.CONTEXT_MAP -> {
                            addKeywords(result, listOf("contains"), "property", 100.0)
                            addNames(result, contextNames(file), "BoundedContext", 90.0)
                            addKeywords(result, CmlKeywords.RELATIONSHIPS, "relationship", 50.0)
                        }
                        Block.TOP_LEVEL -> {
                            addKeywords(result, CmlKeywords.DECLARATIONS, "declaration", 100.0)
                        }
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

    private enum class Block { TOP_LEVEL, CONTEXT_MAP, BOUNDED_CONTEXT }

    companion object {
        private fun lastWordBefore(before: String): String {
            val trimmed = before.trimEnd { it.isLetterOrDigit() || it == '_' }
            return trimmed.trimEnd().takeLastWhile { it.isLetterOrDigit() || it == '_' }
        }

        private fun propertyNameBefore(before: String): String =
            before.trimEnd().removeSuffix("=").trimEnd()
                .takeLastWhile { it.isLetterOrDigit() || it == '_' }

        private fun enclosingBlock(file: PsiFile, offset: Int): Block {
            val before = file.text.substring(0, offset)
            var depth = 0
            var i = before.length - 1
            while (i >= 0) {
                when (before[i]) {
                    '}' -> depth++
                    '{' -> {
                        if (depth == 0) {
                            val kw = before.substring(0, i).trimEnd()
                                .dropLastWhile { it.isLetterOrDigit() || it == '_' }
                                .trimEnd()
                                .takeLastWhile { it.isLetterOrDigit() || it == '_' }
                            return when (kw) {
                                "BoundedContext" -> Block.BOUNDED_CONTEXT
                                "ContextMap" -> Block.CONTEXT_MAP
                                else -> Block.TOP_LEVEL
                            }
                        }
                        depth--
                    }
                }
                i--
            }
            return Block.TOP_LEVEL
        }

        private fun contextNames(file: PsiFile): List<String> {
            val local = PsiTreeUtil.findChildrenOfType(file, CmlBoundedContext::class.java)
                .mapNotNull { it.identifier?.text }
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
            return map.containsStatementList
                .mapNotNull { it.contextRef?.identifier?.text }
                .toSet()
        }
    }
}
