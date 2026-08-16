package io.github.marksosman.dddcontextmapper

import com.intellij.psi.tree.IElementType

class CmlTokenType(debugName: String) : IElementType(debugName, CmlLanguage)

object CmlTokenTypes {
    val KEYWORD = CmlTokenType("KEYWORD")
    val CONSTANT = CmlTokenType("CONSTANT")
    val IDENTIFIER = CmlTokenType("IDENTIFIER")
    val STRING = CmlTokenType("STRING")
    val NUMBER = CmlTokenType("NUMBER")
    val LINE_COMMENT = CmlTokenType("LINE_COMMENT")
    val BLOCK_COMMENT = CmlTokenType("BLOCK_COMMENT")
    val BRACE = CmlTokenType("BRACE")
    val BRACKET = CmlTokenType("BRACKET")
    val OPERATOR = CmlTokenType("OPERATOR")
}
