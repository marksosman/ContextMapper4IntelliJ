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
  IElementType DOMAIN = new CmlElementType("DOMAIN");
  IElementType DOMAIN_REF = new CmlElementType("DOMAIN_REF");
  IElementType NESTED_BLOCK = new CmlElementType("NESTED_BLOCK");
  IElementType PROPERTY = new CmlElementType("PROPERTY");
  IElementType PROPERTY_NAME = new CmlElementType("PROPERTY_NAME");
  IElementType RELATIONSHIP = new CmlElementType("RELATIONSHIP");
  IElementType ROLE = new CmlElementType("ROLE");
  IElementType ROLE_LIST = new CmlElementType("ROLE_LIST");
  IElementType SUBDOMAIN = new CmlElementType("SUBDOMAIN");

  IElementType AT = new CmlTokenType("@");
  IElementType BIARROW = new CmlTokenType("<->");
  IElementType BLOCK_COMMENT = new CmlTokenType("BLOCK_COMMENT");
  IElementType COLON = new CmlTokenType(":");
  IElementType COMMA = new CmlTokenType(",");
  IElementType CONSTANT = new CmlTokenType("CONSTANT");
  IElementType DCOLON = new CmlTokenType("::");
  IElementType DECL_KEYWORD = new CmlTokenType("DECL_KEYWORD");
  IElementType EQ = new CmlTokenType("=");
  IElementType FLOW_KEYWORD = new CmlTokenType("FLOW_KEYWORD");
  IElementType IDENTIFIER = new CmlTokenType("IDENTIFIER");
  IElementType KW_BOUNDED_CONTEXT = new CmlTokenType("BoundedContext");
  IElementType KW_CONTAINS = new CmlTokenType("contains");
  IElementType KW_CONTEXT_MAP = new CmlTokenType("ContextMap");
  IElementType KW_DOMAIN = new CmlTokenType("Domain");
  IElementType KW_IMPLEMENTS = new CmlTokenType("implements");
  IElementType KW_REALIZES = new CmlTokenType("realizes");
  IElementType KW_REFINES = new CmlTokenType("refines");
  IElementType KW_SUBDOMAIN = new CmlTokenType("Subdomain");
  IElementType KW_SUPPORTS = new CmlTokenType("supports");
  IElementType LANGLE = new CmlTokenType("<");
  IElementType LARROW = new CmlTokenType("<-");
  IElementType LBRACE = new CmlTokenType("{");
  IElementType LBRACKET = new CmlTokenType("[");
  IElementType LINE_COMMENT = new CmlTokenType("LINE_COMMENT");
  IElementType LPAREN = new CmlTokenType("(");
  IElementType MINUS = new CmlTokenType("-");
  IElementType NUMBER = new CmlTokenType("NUMBER");
  IElementType PLUS = new CmlTokenType("+");
  IElementType PRIMITIVE = new CmlTokenType("PRIMITIVE");
  IElementType PROP_KEYWORD = new CmlTokenType("PROP_KEYWORD");
  IElementType RANGLE = new CmlTokenType(">");
  IElementType RARROW = new CmlTokenType("->");
  IElementType RBRACE = new CmlTokenType("}");
  IElementType RBRACKET = new CmlTokenType("]");
  IElementType RPAREN = new CmlTokenType(")");
  IElementType SEMI = new CmlTokenType(";");
  IElementType STAR = new CmlTokenType("*");
  IElementType STORY_KEYWORD = new CmlTokenType("STORY_KEYWORD");
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
      else if (type == DOMAIN) {
        return new CmlDomainImpl(node);
      }
      else if (type == DOMAIN_REF) {
        return new CmlDomainRefImpl(node);
      }
      else if (type == NESTED_BLOCK) {
        return new CmlNestedBlockImpl(node);
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
      else if (type == SUBDOMAIN) {
        return new CmlSubdomainImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
