package io.github.marksosman.dddcontextmapper

import com.intellij.codeInsight.AutoPopupController
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class CmlTypedHandler : TypedHandlerDelegate() {

    override fun checkAutoPopup(
        charTyped: Char,
        project: Project,
        editor: Editor,
        file: PsiFile
    ): Result {
        if (file.language != CmlLanguage) return Result.CONTINUE

        val trigger = charTyped.isLetter() ||
                (charTyped == ' ' && lastKeywordBefore(editor) in VALUE_TAKING)

        if (trigger) {
            AutoPopupController.getInstance(project).scheduleAutoPopup(editor)
        }
        return Result.CONTINUE
    }

    private fun lastKeywordBefore(editor: Editor): String {
        val text = editor.document.charsSequence
        var end = editor.caretModel.offset
        while (end > 0 && (text[end - 1].isWhitespace() || text[end - 1] == '=')) end--
        var start = end
        while (start > 0 && (text[start - 1].isLetterOrDigit() || text[start - 1] == '_')) start--
        return text.subSequence(start, end).toString()
    }

    companion object {
        private val VALUE_TAKING = setOf(
            "contains", "implements", "realizes", "refines", "type",
            "knowledgeLevel", "domainVisionStatement", "implementationTechnology",
            "responsibilities", "exposedAggregates", "aggregateRoot"
        )
    }
}
