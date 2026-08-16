package io.github.marksosman.dddcontextmapper

import com.intellij.extapi.psi.ASTWrapperPsiElement
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

class CmlFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, CmlLanguage) {
    override fun getFileType(): FileType = CmlFileType
    override fun toString() = "CML File"
}

class CmlParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer = CmlLexer()

    override fun createParser(project: Project?): PsiParser =
        PsiParser { root, builder ->
            val mark = builder.mark()
            while (!builder.eof()) builder.advanceLexer()
            mark.done(root)
            builder.treeBuilt
        }

    override fun getFileNodeType() = FILE
    override fun getCommentTokens(): TokenSet = COMMENTS
    override fun getStringLiteralElements(): TokenSet = STRINGS
    override fun createElement(node: ASTNode): PsiElement = ASTWrapperPsiElement(node)
    override fun createFile(viewProvider: FileViewProvider): PsiFile = CmlFile(viewProvider)

    companion object {
        val FILE = IFileElementType(CmlLanguage)
        val COMMENTS = TokenSet.create(CmlTokenTypes.LINE_COMMENT, CmlTokenTypes.BLOCK_COMMENT)
        val STRINGS = TokenSet.create(CmlTokenTypes.STRING)
    }
}
