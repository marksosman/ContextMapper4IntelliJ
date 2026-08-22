package io.github.marksosman.dddcontextmapper

import com.intellij.lang.Language
import com.intellij.openapi.editor.Editor
import com.intellij.psi.codeStyle.CodeStyleSettingsManager
import com.intellij.psi.codeStyle.lineIndent.LineIndentProvider

class CmlLineIndentProvider : LineIndentProvider {

    override fun getLineIndent(
        project: com.intellij.openapi.project.Project,
        editor: Editor,
        language: Language?,
        offset: Int
    ): String? {
        if (language != CmlLanguage) return null
        val text = editor.document.charsSequence
        var depth = 0
        for (i in 0 until offset) {
            when (text[i]) {
                '{' -> depth++
                '}' -> depth--
            }
        }
        if (depth < 0) depth = 0
        val settings = com.intellij.application.options.CodeStyle.getSettings(project)
        val size = settings.getIndentOptions(null).INDENT_SIZE
        return " ".repeat(depth * size)
    }

    override fun isSuitableFor(language: Language?): Boolean = language == CmlLanguage
}
