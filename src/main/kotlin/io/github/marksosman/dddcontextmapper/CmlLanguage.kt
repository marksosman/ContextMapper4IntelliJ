package io.github.marksosman.dddcontextmapper

import com.intellij.lang.Language

object CmlLanguage : Language("CML") {
    private fun readResolve(): Any = CmlLanguage
    override fun getDisplayName(): String = "Context Mapper DSL"
}
