package io.github.marksosman.dddcontextmapper

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object CmlFileType : LanguageFileType(CmlLanguage) {
    override fun getName(): String = "CML"
    override fun getDescription(): String = "Context Mapper DSL"
    override fun getDefaultExtension(): String = "cml"
    override fun getIcon(): Icon = CmlIcons.FILE
}
