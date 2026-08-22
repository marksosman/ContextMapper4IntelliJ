package io.github.marksosman.dddcontextmapper

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import io.github.marksosman.dddcontextmapper.psi.CmlTypes

class CmlBraceMatcher : PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> = PAIRS

    override fun isPairedBracesAllowedBeforeType(
        lbraceType: IElementType,
        contextType: IElementType?
    ): Boolean = true

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int =
        openingBraceOffset

    companion object {
        private val PAIRS = arrayOf(
            BracePair(CmlTypes.LBRACE, CmlTypes.RBRACE, true),
            BracePair(CmlTypes.LBRACKET, CmlTypes.RBRACKET, false)
        )
    }
}
