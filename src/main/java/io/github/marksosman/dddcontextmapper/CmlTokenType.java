package io.github.marksosman.dddcontextmapper;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CmlTokenType extends IElementType {
    public CmlTokenType(@NotNull @NonNls String debugName) {
        super(debugName, CmlLanguage.INSTANCE);
    }
}
