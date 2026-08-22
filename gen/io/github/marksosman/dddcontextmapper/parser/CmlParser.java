// This is a generated file. Not intended for manual editing.
package io.github.marksosman.dddcontextmapper.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static io.github.marksosman.dddcontextmapper.psi.CmlTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class CmlParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return cmlFile(b, l + 1);
  }

  /* ********************************************************** */
  // BIARROW | RARROW | LARROW
  static boolean arrow(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "arrow")) return false;
    boolean r;
    r = consumeToken(b, BIARROW);
    if (!r) r = consumeToken(b, RARROW);
    if (!r) r = consumeToken(b, LARROW);
    return r;
  }

  /* ********************************************************** */
  // KW_BOUNDED_CONTEXT IDENTIFIER LBRACE property* RBRACE
  public static boolean boundedContext(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boundedContext")) return false;
    if (!nextTokenIs(b, KW_BOUNDED_CONTEXT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BOUNDED_CONTEXT, null);
    r = consumeTokens(b, 1, KW_BOUNDED_CONTEXT, IDENTIFIER, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, boundedContext_3(b, l + 1));
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // property*
  private static boolean boundedContext_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boundedContext_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!property(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "boundedContext_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // item_*
  static boolean cmlFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "cmlFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "cmlFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // KW_CONTAINS contextRef
  public static boolean containsStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "containsStatement")) return false;
    if (!nextTokenIs(b, KW_CONTAINS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONTAINS_STATEMENT, null);
    r = consumeToken(b, KW_CONTAINS);
    p = r; // pin = 1
    r = r && contextRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // KW_CONTEXT_MAP IDENTIFIER LBRACE contextMapEntry* RBRACE
  public static boolean contextMap(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMap")) return false;
    if (!nextTokenIs(b, KW_CONTEXT_MAP)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONTEXT_MAP, null);
    r = consumeTokens(b, 1, KW_CONTEXT_MAP, IDENTIFIER, LBRACE);
    p = r; // pin = 1
    r = r && report_error_(b, contextMap_3(b, l + 1));
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // contextMapEntry*
  private static boolean contextMap_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMap_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!contextMapEntry(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "contextMap_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // containsStatement | relationship
  static boolean contextMapEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMapEntry")) return false;
    if (!nextTokenIs(b, "", IDENTIFIER, KW_CONTAINS)) return false;
    boolean r;
    r = containsStatement(b, l + 1);
    if (!r) r = relationship(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean contextRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextRef")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, CONTEXT_REF, r);
    return r;
  }

  /* ********************************************************** */
  // contextMap | boundedContext
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    if (!nextTokenIs(b, "", KW_BOUNDED_CONTEXT, KW_CONTEXT_MAP)) return false;
    boolean r;
    r = contextMap(b, l + 1);
    if (!r) r = boundedContext(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // propertyName EQ propertyValue
  public static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PROPERTY, null);
    r = propertyName(b, l + 1);
    r = r && consumeToken(b, EQ);
    p = r; // pin = 2
    r = r && propertyValue(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean propertyName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "propertyName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, PROPERTY_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // STRING | NUMBER | IDENTIFIER
  static boolean propertyValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "propertyValue")) return false;
    boolean r;
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  /* ********************************************************** */
  // contextRef roleList? arrow roleList? contextRef
  public static boolean relationship(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationship")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = contextRef(b, l + 1);
    r = r && relationship_1(b, l + 1);
    r = r && arrow(b, l + 1);
    r = r && relationship_3(b, l + 1);
    r = r && contextRef(b, l + 1);
    exit_section_(b, m, RELATIONSHIP, r);
    return r;
  }

  // roleList?
  private static boolean relationship_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationship_1")) return false;
    roleList(b, l + 1);
    return true;
  }

  // roleList?
  private static boolean relationship_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationship_3")) return false;
    roleList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean role(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "role")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, ROLE, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET role (COMMA role)* RBRACKET
  public static boolean roleList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "roleList")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ROLE_LIST, null);
    r = consumeToken(b, LBRACKET);
    p = r; // pin = 1
    r = r && report_error_(b, role(b, l + 1));
    r = p && report_error_(b, roleList_2(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (COMMA role)*
  private static boolean roleList_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "roleList_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!roleList_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "roleList_2", c)) break;
    }
    return true;
  }

  // COMMA role
  private static boolean roleList_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "roleList_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && role(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
