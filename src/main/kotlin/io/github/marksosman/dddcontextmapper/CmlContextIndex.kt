package io.github.marksosman.dddcontextmapper

import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.indexing.DataIndexer
import com.intellij.util.indexing.FileBasedIndex
import com.intellij.util.indexing.FileBasedIndexExtension
import com.intellij.util.indexing.FileContent
import com.intellij.util.indexing.ID
import com.intellij.util.io.EnumeratorStringDescriptor
import com.intellij.util.io.KeyDescriptor

class CmlContextIndex : FileBasedIndexExtension<String, Void>() {

    override fun getName(): ID<String, Void> = NAME

    override fun getIndexer(): DataIndexer<String, Void, FileContent> =
        DataIndexer { content ->
            DECLARATION_PATTERN.findAll(content.contentAsText)
                .map { it.groupValues[1] }
                .associateWith { null }
        }

    override fun getKeyDescriptor(): KeyDescriptor<String> =
        EnumeratorStringDescriptor.INSTANCE

    override fun getValueExternalizer() = com.intellij.util.io.VoidDataExternalizer.INSTANCE

    override fun getVersion(): Int = 1

    override fun getInputFilter() =
        FileBasedIndex.InputFilter { it.fileType == CmlFileType }

    override fun dependsOnFileContent(): Boolean = true

    companion object {
        val NAME: ID<String, Void> = ID.create("cml.bounded.context.index")

        private val DECLARATION_PATTERN =
            Regex("""\bBoundedContext\s+([A-Za-z_][A-Za-z0-9_]*)""")

        fun allContextNames(project: Project): List<String> =
            FileBasedIndex.getInstance()
                .getAllKeys(NAME, project)
                .filter { name ->
                    FileBasedIndex.getInstance()
                        .getContainingFiles(NAME, name, GlobalSearchScope.projectScope(project))
                        .isNotEmpty()
                }
                .sorted()
    }
}
