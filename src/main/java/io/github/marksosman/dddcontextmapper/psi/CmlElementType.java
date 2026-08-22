package io.github.marksosman.dddcontextmapper.psi;

import com.intellij.psi.tree.IElementType;
import io.github.marksosman.dddcontextmapper.CmlLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CmlElementType extends IElementType {
    public CmlElementType(@NotNull @NonNls String debugName) {
        super(debugName, CmlLanguage.INSTANCE);
    }
}
