package io.github.marksosman.dddcontextmapper

import com.intellij.lexer.LexerBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType

private val KEYWORDS = setOf(
    "ContextMap", "BoundedContext", "Domain", "Subdomain", "Aggregate",
    "Entity", "ValueObject", "Service", "Repository", "Enum", "Module",
    "DomainEvent", "CommandEvent", "Application", "UseCase", "UserStory",
    "contains", "implements", "realizes", "refines", "type", "state",
    "domainVisionStatement", "responsibilities", "implementationTechnology",
    "knowledgeLevel", "exposedAggregates", "upstream", "downstream",
    "Partnership", "SharedKernel", "CustomerSupplier", "UpstreamDownstream",
    "OpenHostService", "PublishedLanguage", "AntiCorruptionLayer", "Conformist",
    "aggregateRoot", "not", "key", "def", "abstract", "extends"
)

class CmlLexer : LexerBase() {
    private var buf: CharSequence = ""
    private var end = 0
    private var tokStart = 0
    private var tokEnd = 0
    private var tokType: IElementType? = null

    override fun start(buffer: CharSequence, startOffset: Int, endOffset: Int, initialState: Int) {
        buf = buffer
        end = endOffset
        tokEnd = startOffset
        advance()
    }

    override fun getState() = 0
    override fun getTokenType() = tokType
    override fun getTokenStart() = tokStart
    override fun getTokenEnd() = tokEnd
    override fun getBufferSequence() = buf
    override fun getBufferEnd() = end

    override fun advance() {
        tokStart = tokEnd
        if (tokStart >= end) {
            tokType = null
            return
        }
        val c = buf[tokStart]
        val next = if (tokStart + 1 < end) buf[tokStart + 1] else '\u0000'

        when {
            c.isWhitespace() -> {
                tokEnd = scanWhile(tokStart) { it.isWhitespace() }
                tokType = TokenType.WHITE_SPACE
            }
            c == '/' && next == '/' -> {
                tokEnd = scanWhile(tokStart) { it != '\n' }
                tokType = CmlTokenTypes.LINE_COMMENT
            }
            c == '/' && next == '*' -> {
                var i = tokStart + 2
                while (i + 1 < end && !(buf[i] == '*' && buf[i + 1] == '/')) i++
                tokEnd = if (i + 1 < end) i + 2 else end
                tokType = CmlTokenTypes.BLOCK_COMMENT
            }
            c == '"' -> {
                var i = tokStart + 1
                while (i < end && buf[i] != '"' && buf[i] != '\n') i++
                tokEnd = if (i < end && buf[i] == '"') i + 1 else i
                tokType = CmlTokenTypes.STRING
            }
            c.isDigit() -> {
                tokEnd = scanWhile(tokStart) { it.isDigit() || it == '.' }
                tokType = CmlTokenTypes.NUMBER
            }
            c.isLetter() || c == '_' -> {
                tokEnd = scanWhile(tokStart) { it.isLetterOrDigit() || it == '_' }
                val word = buf.subSequence(tokStart, tokEnd).toString()
                tokType = when {
                    word in KEYWORDS -> CmlTokenTypes.KEYWORD
                    word.none { it.isLowerCase() } -> CmlTokenTypes.CONSTANT
                    else -> CmlTokenTypes.IDENTIFIER
                }
            }
            c == '{' || c == '}' -> single(CmlTokenTypes.BRACE)
            c == '[' || c == ']' -> single(CmlTokenTypes.BRACKET)
            else -> {
                tokEnd = scanWhile(tokStart) { it in "<>-=/:,;." }
                if (tokEnd == tokStart) tokEnd = tokStart + 1
                tokType = CmlTokenTypes.OPERATOR
            }
        }
    }

    private fun single(type: IElementType) {
        tokEnd = tokStart + 1
        tokType = type
    }

    private inline fun scanWhile(from: Int, pred: (Char) -> Boolean): Int {
        var i = from
        while (i < end && pred(buf[i])) i++
        return i
    }
}
