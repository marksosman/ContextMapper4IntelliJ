package io.github.marksosman.dddcontextmapper

import com.intellij.codeInsight.AutoPopupController
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class CmlTypedHandler : TypedHandlerDelegate() {

    override fun charTyped(
        c: Char,
        project: Project,
        editor: Editor,
        file: PsiFile
    ): Result {
        if (file.language != CmlLanguage) return Result.CONTINUE

        if (c == '<') {
            val caret = editor.caretModel.offset
            if (wordEndingAt(editor, caret - 1) in COLLECTIONS) {
                editor.document.insertString(caret, ">")
                return Result.STOP
            }
        }
        return Result.CONTINUE
    }

    override fun checkAutoPopup(
        charTyped: Char,
        project: Project,
        editor: Editor,
        file: PsiFile
    ): Result {
        if (file.language != CmlLanguage) return Result.CONTINUE

        if (charTyped.isLetter() || charTyped in TRIGGERS) {
            AutoPopupController.getInstance(project).scheduleAutoPopup(editor)
        }
        return Result.CONTINUE
    }

    private fun wordEndingAt(editor: Editor, end: Int): String {
        if (end <= 0) return ""
        val text = editor.document.charsSequence
        var start = end
        while (start > 0 && (text[start - 1].isLetterOrDigit() || text[start - 1] == '_')) start--
        return text.subSequence(start, end).toString()
    }

    companion object {
        private val TRIGGERS = setOf(' ', '=', '[', ']', ',', '<', '-')

        private val COLLECTIONS = setOf("List", "Set", "Bag", "Map", "Collection")
    }
}
