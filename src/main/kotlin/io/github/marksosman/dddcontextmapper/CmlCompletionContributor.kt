package io.github.marksosman.dddcontextmapper

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext

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
                    add(result, CmlKeywords.DECLARATIONS, "declaration")
                    add(result, CmlKeywords.PROPERTIES, "property")
                    add(result, CmlKeywords.RELATIONSHIPS, "relationship")
                }

                private fun add(
                    result: CompletionResultSet,
                    words: List<String>,
                    kind: String
                ) {
                    words.forEach { word ->
                        result.addElement(
                            LookupElementBuilder.create(word)
                                .bold()
                                .withTypeText(kind)
                        )
                    }
                }
            }
        )
    }
}