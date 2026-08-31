package io.github.marksosman.dddcontextmapper

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import io.github.marksosman.dddcontextmapper.psi.CmlTypes

class CmlFoldingBuilder : FoldingBuilderEx(), DumbAware {

    override fun buildFoldRegions(
        root: PsiElement,
        document: Document,
        quick: Boolean
    ): Array<FoldingDescriptor> {
        val out = mutableListOf<FoldingDescriptor>()
        collect(root.node, out)
        return out.toTypedArray()
    }

    private fun collect(node: ASTNode, out: MutableList<FoldingDescriptor>) {
        val placeholder = ARROWS[node.elementType]
        if (placeholder != null && node.text != placeholder) {
            out.add(FoldingDescriptor(node, node.textRange, null, placeholder))
        }
        var child = node.firstChildNode
        while (child != null) {
            collect(child, out)
            child = child.treeNext
        }
    }

    override fun getPlaceholderText(node: ASTNode): String? = ARROWS[node.elementType]

    override fun isCollapsedByDefault(node: ASTNode): Boolean = true

    companion object {
        private val ARROWS = mapOf(
            CmlTypes.BIARROW to "\u2194",
            CmlTypes.RARROW to "\u2192",
            CmlTypes.LARROW to "\u2190"
        )
    }
}
