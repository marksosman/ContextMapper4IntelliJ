package io.github.marksosman.dddcontextmapper

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import io.github.marksosman.dddcontextmapper.parser.CmlParser
import io.github.marksosman.dddcontextmapper.psi.CmlTypes

class CmlFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, CmlLanguage) {
    override fun getFileType(): FileType = CmlFileType
    override fun toString() = "CML File"
}

class CmlParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer = CmlLexerAdapter()

    override fun createParser(project: Project?): PsiParser = CmlParser()

    override fun getFileNodeType() = FILE
    override fun getCommentTokens(): TokenSet = COMMENTS
    override fun getStringLiteralElements(): TokenSet = STRINGS

    override fun createElement(node: ASTNode): PsiElement =
        CmlTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = CmlFile(viewProvider)

    companion object {
        val FILE = IFileElementType(CmlLanguage)
        val COMMENTS = TokenSet.create(CmlTypes.LINE_COMMENT, CmlTypes.BLOCK_COMMENT)
        val STRINGS = TokenSet.create(CmlTypes.STRING)
    }
}
