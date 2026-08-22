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

public class CmlContextMapImpl extends ASTWrapperPsiElement implements CmlContextMap {

  public CmlContextMapImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CmlVisitor visitor) {
    visitor.visitContextMap(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CmlVisitor) accept((CmlVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<CmlContainsStatement> getContainsStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CmlContainsStatement.class);
  }

  @Override
  @NotNull
  public List<CmlRelationship> getRelationshipList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, CmlRelationship.class);
  }

  @Override
  @Nullable
  public PsiElement getIdentifier() {
    return findChildByType(IDENTIFIER);
  }

}
