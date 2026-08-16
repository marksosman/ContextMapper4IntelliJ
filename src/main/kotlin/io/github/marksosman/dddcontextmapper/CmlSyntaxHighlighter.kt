package io.github.marksosman.dddcontextmapper

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors as D
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType

class CmlSyntaxHighlighter : SyntaxHighlighterBase() {

    override fun getHighlightingLexer(): Lexer = CmlLexer()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> =
        when (tokenType) {
            CmlTokenTypes.KEYWORD -> arrayOf(KEYWORD)
            CmlTokenTypes.CONSTANT -> arrayOf(CONSTANT)
            CmlTokenTypes.STRING -> arrayOf(STRING)
            CmlTokenTypes.NUMBER -> arrayOf(NUMBER)
            CmlTokenTypes.LINE_COMMENT -> arrayOf(LINE_COMMENT)
            CmlTokenTypes.BLOCK_COMMENT -> arrayOf(BLOCK_COMMENT)
            CmlTokenTypes.BRACE -> arrayOf(BRACES)
            CmlTokenTypes.BRACKET -> arrayOf(BRACKETS)
            CmlTokenTypes.OPERATOR -> arrayOf(OPERATOR)
            else -> TextAttributesKey.EMPTY_ARRAY
        }

    companion object {
        val KEYWORD = createTextAttributesKey("CML_KEYWORD", D.KEYWORD)
        val CONSTANT = createTextAttributesKey("CML_CONSTANT", D.CONSTANT)
        val STRING = createTextAttributesKey("CML_STRING", D.STRING)
        val NUMBER = createTextAttributesKey("CML_NUMBER", D.NUMBER)
        val LINE_COMMENT = createTextAttributesKey("CML_LINE_COMMENT", D.LINE_COMMENT)
        val BLOCK_COMMENT = createTextAttributesKey("CML_BLOCK_COMMENT", D.BLOCK_COMMENT)
        val BRACES = createTextAttributesKey("CML_BRACES", D.BRACES)
        val BRACKETS = createTextAttributesKey("CML_BRACKETS", D.BRACKETS)
        val OPERATOR = createTextAttributesKey("CML_OPERATOR", D.OPERATION_SIGN)
    }
}

class CmlSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, file: VirtualFile?): SyntaxHighlighter =
        CmlSyntaxHighlighter()
}
