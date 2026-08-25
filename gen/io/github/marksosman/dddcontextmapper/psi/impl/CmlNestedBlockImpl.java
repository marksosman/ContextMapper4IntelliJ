// This is a generated file. Not intended for manual editing.
package io.github.marksosman.dddcontextmapper.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.marksosman.dddcontextmapper.psi.CmlTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.github.marksosman.dddcontextmapper.psi.*;

public class CmlNestedBlockImpl extends ASTWrapperPsiElement implements CmlNestedBlock {

  public CmlNestedBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CmlVisitor visitor) {
    visitor.visitNestedBlock(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CmlVisitor) accept((CmlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CmlNestedBlock> getNestedBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CmlNestedBlock.class);
  }

  @Override
  @NotNull
  public List<CmlProperty> getPropertyList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CmlProperty.class);
  }

}
