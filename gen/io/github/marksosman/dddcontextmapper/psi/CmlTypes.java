// This is a generated file. Not intended for manual editing.
package io.github.marksosman.dddcontextmapper.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.github.marksosman.dddcontextmapper.CmlTokenType;
import io.github.marksosman.dddcontextmapper.psi.impl.*;

public interface CmlTypes {

  IElementType BOUNDED_CONTEXT = new CmlElementType("BOUNDED_CONTEXT");
  IElementType CONTAINS_STATEMENT = new CmlElementType("CONTAINS_STATEMENT");
  IElementType CONTEXT_MAP = new CmlElementType("CONTEXT_MAP");
  IElementType CONTEXT_REF = new CmlElementType("CONTEXT_REF");
  IElementType PROPERTY = new CmlElementType("PROPERTY");
  IElementType PROPERTY_NAME = new CmlElementType("PROPERTY_NAME");
  IElementType RELATIONSHIP = new CmlElementType("RELATIONSHIP");
  IElementType ROLE = new CmlElementType("ROLE");
  IElementType ROLE_LIST = new CmlElementType("ROLE_LIST");

  IElementType BIARROW = new CmlTokenType("<->");
  IElementType BLOCK_COMMENT = new CmlTokenType("BLOCK_COMMENT");
  IElementType COMMA = new CmlTokenType(",");
  IElementType EQ = new CmlTokenType("=");
  IElementType IDENTIFIER = new CmlTokenType("IDENTIFIER");
  IElementType KW_BOUNDED_CONTEXT = new CmlTokenType("BoundedContext");
  IElementType KW_CONTAINS = new CmlTokenType("contains");
  IElementType KW_CONTEXT_MAP = new CmlTokenType("ContextMap");
  IElementType LARROW = new CmlTokenType("<-");
  IElementType LBRACE = new CmlTokenType("{");
  IElementType LBRACKET = new CmlTokenType("[");
  IElementType LINE_COMMENT = new CmlTokenType("LINE_COMMENT");
  IElementType NUMBER = new CmlTokenType("NUMBER");
  IElementType RARROW = new CmlTokenType("->");
  IElementType RBRACE = new CmlTokenType("}");
  IElementType RBRACKET = new CmlTokenType("]");
  IElementType STRING = new CmlTokenType("STRING");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == BOUNDED_CONTEXT) {
        return new CmlBoundedContextImpl(node);
      }
      else if (type == CONTAINS_STATEMENT) {
        return new CmlContainsStatementImpl(node);
      }
      else if (type == CONTEXT_MAP) {
        return new CmlContextMapImpl(node);
      }
      else if (type == CONTEXT_REF) {
        return new CmlContextRefImpl(node);
      }
      else if (type == PROPERTY) {
        return new CmlPropertyImpl(node);
      }
      else if (type == PROPERTY_NAME) {
        return new CmlPropertyNameImpl(node);
      }
      else if (type == RELATIONSHIP) {
        return new CmlRelationshipImpl(node);
      }
      else if (type == ROLE) {
        return new CmlRoleImpl(node);
      }
      else if (type == ROLE_LIST) {
        return new CmlRoleListImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
