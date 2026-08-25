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
import io.github.marksosman.dddcontextmapper.psi.CmlTypes

class CmlSyntaxHighlighter : SyntaxHighlighterBase() {

    override fun getHighlightingLexer(): Lexer = CmlLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> =
        when (tokenType) {
            CmlTypes.KW_CONTEXT_MAP,
            CmlTypes.KW_BOUNDED_CONTEXT,
            CmlTypes.KW_DOMAIN,
            CmlTypes.KW_SUBDOMAIN,
            CmlTypes.DECL_KEYWORD -> arrayOf(DECLARATION)

            CmlTypes.KW_CONTAINS,
            CmlTypes.KW_IMPLEMENTS,
            CmlTypes.KW_REFINES,
            CmlTypes.KW_REALIZES,
            CmlTypes.KW_SUPPORTS,
            CmlTypes.PROP_KEYWORD -> arrayOf(PROPERTY)

            CmlTypes.FLOW_KEYWORD,
            CmlTypes.STORY_KEYWORD -> arrayOf(FLOW)

            CmlTypes.PRIMITIVE -> arrayOf(PRIMITIVE)
            CmlTypes.CONSTANT -> arrayOf(CONSTANT)

            CmlTypes.STRING -> arrayOf(STRING)
            CmlTypes.NUMBER -> arrayOf(NUMBER)
            CmlTypes.LINE_COMMENT -> arrayOf(LINE_COMMENT)
            CmlTypes.BLOCK_COMMENT -> arrayOf(BLOCK_COMMENT)

            CmlTypes.LBRACE, CmlTypes.RBRACE -> arrayOf(BRACES)
            CmlTypes.LBRACKET, CmlTypes.RBRACKET -> arrayOf(BRACKETS)
            CmlTypes.LPAREN, CmlTypes.RPAREN -> arrayOf(PARENTHESES)

            CmlTypes.EQ, CmlTypes.COMMA, CmlTypes.SEMI, CmlTypes.COLON,
            CmlTypes.DCOLON, CmlTypes.MINUS, CmlTypes.AT, CmlTypes.PLUS,
            CmlTypes.STAR, CmlTypes.LANGLE, CmlTypes.RANGLE,
            CmlTypes.BIARROW, CmlTypes.RARROW, CmlTypes.LARROW -> arrayOf(OPERATOR)

            CmlTypes.IDENTIFIER -> arrayOf(IDENTIFIER)

            else -> TextAttributesKey.EMPTY_ARRAY
        }

    companion object {
        val DECLARATION = createTextAttributesKey("CML_DECLARATION", D.KEYWORD)
        val PROPERTY = createTextAttributesKey("CML_PROPERTY", D.INSTANCE_FIELD)
        val FLOW = createTextAttributesKey("CML_FLOW", D.METADATA)
        val PRIMITIVE = createTextAttributesKey("CML_PRIMITIVE", D.KEYWORD)
        val CONSTANT = createTextAttributesKey("CML_CONSTANT", D.CONSTANT)
        val IDENTIFIER = createTextAttributesKey("CML_IDENTIFIER", D.IDENTIFIER)
        val STRING = createTextAttributesKey("CML_STRING", D.STRING)
        val NUMBER = createTextAttributesKey("CML_NUMBER", D.NUMBER)
        val LINE_COMMENT = createTextAttributesKey("CML_LINE_COMMENT", D.LINE_COMMENT)
        val BLOCK_COMMENT = createTextAttributesKey("CML_BLOCK_COMMENT", D.BLOCK_COMMENT)
        val BRACES = createTextAttributesKey("CML_BRACES", D.BRACES)
        val BRACKETS = createTextAttributesKey("CML_BRACKETS", D.BRACKETS)
        val PARENTHESES = createTextAttributesKey("CML_PARENTHESES", D.PARENTHESES)
        val OPERATOR = createTextAttributesKey("CML_OPERATOR", D.OPERATION_SIGN)
    }
}

class CmlSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
    override fun getSyntaxHighlighter(project: Project?, file: VirtualFile?): SyntaxHighlighter =
        CmlSyntaxHighlighter()
}
