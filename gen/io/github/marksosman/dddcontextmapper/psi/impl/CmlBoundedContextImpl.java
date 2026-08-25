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

public class CmlBoundedContextImpl extends ASTWrapperPsiElement implements CmlBoundedContext {

  public CmlBoundedContextImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CmlVisitor visitor) {
    visitor.visitBoundedContext(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CmlVisitor) accept((CmlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CmlContextRef> getContextRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CmlContextRef.class);
  }

  @Override
  @NotNull
  public List<CmlDomainRef> getDomainRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CmlDomainRef.class);
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
