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
  // nestedBlock | property | opaque_
  static boolean bodyEntry_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "bodyEntry_")) return false;
    boolean r;
    r = nestedBlock(b, l + 1);
    if (!r) r = property(b, l + 1);
    if (!r) r = opaque_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // KW_BOUNDED_CONTEXT name_ contextHeader_* LBRACE bodyEntry_* RBRACE
  public static boolean boundedContext(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boundedContext")) return false;
    if (!nextTokenIs(b, KW_BOUNDED_CONTEXT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BOUNDED_CONTEXT, null);
    r = consumeToken(b, KW_BOUNDED_CONTEXT);
    p = r; // pin = 1
    r = r && report_error_(b, name_(b, l + 1));
    r = p && report_error_(b, boundedContext_2(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, LBRACE)) && r;
    r = p && report_error_(b, boundedContext_4(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // contextHeader_*
  private static boolean boundedContext_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boundedContext_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!contextHeader_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "boundedContext_2", c)) break;
    }
    return true;
  }

  // bodyEntry_*
  private static boolean boundedContext_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boundedContext_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!bodyEntry_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "boundedContext_4", c)) break;
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
  // KW_CONTAINS contextRef (COMMA contextRef)*
  public static boolean containsStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "containsStatement")) return false;
    if (!nextTokenIs(b, KW_CONTAINS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONTAINS_STATEMENT, null);
    r = consumeToken(b, KW_CONTAINS);
    p = r; // pin = 1
    r = r && report_error_(b, contextRef(b, l + 1));
    r = p && containsStatement_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (COMMA contextRef)*
  private static boolean containsStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "containsStatement_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!containsStatement_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "containsStatement_2", c)) break;
    }
    return true;
  }

  // COMMA contextRef
  private static boolean containsStatement_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "containsStatement_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && contextRef(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // implementsClause | refinesClause | realizesClause
  static boolean contextHeader_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextHeader_")) return false;
    boolean r;
    r = implementsClause(b, l + 1);
    if (!r) r = refinesClause(b, l + 1);
    if (!r) r = realizesClause(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // KW_CONTEXT_MAP name_? LBRACE contextMapEntry* RBRACE
  public static boolean contextMap(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMap")) return false;
    if (!nextTokenIs(b, KW_CONTEXT_MAP)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, CONTEXT_MAP, null);
    r = consumeToken(b, KW_CONTEXT_MAP);
    p = r; // pin = 1
    r = r && report_error_(b, contextMap_1(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LBRACE)) && r;
    r = p && report_error_(b, contextMap_3(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // name_?
  private static boolean contextMap_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMap_1")) return false;
    name_(b, l + 1);
    return true;
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
  // containsStatement | relationship | property
  static boolean contextMapEntry(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextMapEntry")) return false;
    boolean r;
    r = containsStatement(b, l + 1);
    if (!r) r = relationship(b, l + 1);
    if (!r) r = property(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER | CONSTANT
  public static boolean contextRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "contextRef")) return false;
    if (!nextTokenIs(b, "<context ref>", CONSTANT, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONTEXT_REF, "<context ref>");
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, CONSTANT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KW_DOMAIN name_ LBRACE domainEntry_* RBRACE
  public static boolean domain(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "domain")) return false;
    if (!nextTokenIs(b, KW_DOMAIN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DOMAIN, null);
    r = consumeToken(b, KW_DOMAIN);
    p = r; // pin = 1
    r = r && report_error_(b, name_(b, l + 1));
    r = p && report_error_(b, consumeToken(b, LBRACE)) && r;
    r = p && report_error_(b, domain_3(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // domainEntry_*
  private static boolean domain_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "domain_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!domainEntry_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "domain_3", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // subdomain | bodyEntry_
  static boolean domainEntry_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "domainEntry_")) return false;
    boolean r;
    r = subdomain(b, l + 1);
    if (!r) r = bodyEntry_(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER | CONSTANT
  public static boolean domainRef(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "domainRef")) return false;
    if (!nextTokenIs(b, "<domain ref>", CONSTANT, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DOMAIN_REF, "<domain ref>");
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, CONSTANT);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // KW_IMPLEMENTS domainRef (COMMA domainRef)*
  static boolean implementsClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implementsClause")) return false;
    if (!nextTokenIs(b, KW_IMPLEMENTS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, KW_IMPLEMENTS);
    p = r; // pin = 1
    r = r && report_error_(b, domainRef(b, l + 1));
    r = p && implementsClause_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (COMMA domainRef)*
  private static boolean implementsClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implementsClause_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!implementsClause_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "implementsClause_2", c)) break;
    }
    return true;
  }

  // COMMA domainRef
  private static boolean implementsClause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "implementsClause_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && domainRef(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // contextMap | boundedContext | domain | subdomain | nestedBlock
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = contextMap(b, l + 1);
    if (!r) r = boundedContext(b, l + 1);
    if (!r) r = domain(b, l + 1);
    if (!r) r = subdomain(b, l + 1);
    if (!r) r = nestedBlock(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER | CONSTANT
  static boolean name_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "name_")) return false;
    if (!nextTokenIs(b, "", CONSTANT, IDENTIFIER)) return false;
    boolean r;
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, CONSTANT);
    return r;
  }

  /* ********************************************************** */
  // DECL_KEYWORD name_? nestedBody_?
  public static boolean nestedBlock(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nestedBlock")) return false;
    if (!nextTokenIs(b, DECL_KEYWORD)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, NESTED_BLOCK, null);
    r = consumeToken(b, DECL_KEYWORD);
    p = r; // pin = 1
    r = r && report_error_(b, nestedBlock_1(b, l + 1));
    r = p && nestedBlock_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // name_?
  private static boolean nestedBlock_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nestedBlock_1")) return false;
    name_(b, l + 1);
    return true;
  }

  // nestedBody_?
  private static boolean nestedBlock_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nestedBlock_2")) return false;
    nestedBody_(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LBRACE bodyEntry_* RBRACE
  static boolean nestedBody_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nestedBody_")) return false;
    if (!nextTokenIs(b, LBRACE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACE);
    r = r && nestedBody__1(b, l + 1);
    r = r && consumeToken(b, RBRACE);
    exit_section_(b, m, null, r);
    return r;
  }

  // bodyEntry_*
  private static boolean nestedBody__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "nestedBody__1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!bodyEntry_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "nestedBody__1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER | PROP_KEYWORD | FLOW_KEYWORD | STORY_KEYWORD
  //                   | PRIMITIVE | CONSTANT | STRING | NUMBER | DECL_KEYWORD
  //                   | LBRACKET | RBRACKET | LPAREN | RPAREN | LANGLE | RANGLE
  //                   | EQ | COMMA | SEMI | COLON | DCOLON | MINUS | AT | PLUS | STAR
  //                   | BIARROW | RARROW | LARROW
  //                   | KW_IMPLEMENTS | KW_REFINES | KW_REALIZES | KW_SUPPORTS | KW_CONTAINS
  static boolean opaque_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "opaque_")) return false;
    boolean r;
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, PROP_KEYWORD);
    if (!r) r = consumeToken(b, FLOW_KEYWORD);
    if (!r) r = consumeToken(b, STORY_KEYWORD);
    if (!r) r = consumeToken(b, PRIMITIVE);
    if (!r) r = consumeToken(b, CONSTANT);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, DECL_KEYWORD);
    if (!r) r = consumeToken(b, LBRACKET);
    if (!r) r = consumeToken(b, RBRACKET);
    if (!r) r = consumeToken(b, LPAREN);
    if (!r) r = consumeToken(b, RPAREN);
    if (!r) r = consumeToken(b, LANGLE);
    if (!r) r = consumeToken(b, RANGLE);
    if (!r) r = consumeToken(b, EQ);
    if (!r) r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, SEMI);
    if (!r) r = consumeToken(b, COLON);
    if (!r) r = consumeToken(b, DCOLON);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, AT);
    if (!r) r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, STAR);
    if (!r) r = consumeToken(b, BIARROW);
    if (!r) r = consumeToken(b, RARROW);
    if (!r) r = consumeToken(b, LARROW);
    if (!r) r = consumeToken(b, KW_IMPLEMENTS);
    if (!r) r = consumeToken(b, KW_REFINES);
    if (!r) r = consumeToken(b, KW_REALIZES);
    if (!r) r = consumeToken(b, KW_SUPPORTS);
    if (!r) r = consumeToken(b, KW_CONTAINS);
    return r;
  }

  /* ********************************************************** */
  // propertyName EQ? propertyValue (COMMA propertyValue)*
  public static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    if (!nextTokenIs(b, "<property>", IDENTIFIER, PROP_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROPERTY, "<property>");
    r = propertyName(b, l + 1);
    r = r && property_1(b, l + 1);
    r = r && propertyValue(b, l + 1);
    r = r && property_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // EQ?
  private static boolean property_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_1")) return false;
    consumeToken(b, EQ);
    return true;
  }

  // (COMMA propertyValue)*
  private static boolean property_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_3")) return false;
    while (true) {
      int c = current_position_(b);
      if (!property_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "property_3", c)) break;
    }
    return true;
  }

  // COMMA propertyValue
  private static boolean property_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && propertyValue(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PROP_KEYWORD | IDENTIFIER
  public static boolean propertyName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "propertyName")) return false;
    if (!nextTokenIs(b, "<property name>", IDENTIFIER, PROP_KEYWORD)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PROPERTY_NAME, "<property name>");
    r = consumeToken(b, PROP_KEYWORD);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // STRING | NUMBER | CONSTANT | IDENTIFIER
  static boolean propertyValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "propertyValue")) return false;
    boolean r;
    r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, NUMBER);
    if (!r) r = consumeToken(b, CONSTANT);
    if (!r) r = consumeToken(b, IDENTIFIER);
    return r;
  }

  /* ********************************************************** */
  // KW_REALIZES contextRef (COMMA contextRef)*
  static boolean realizesClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "realizesClause")) return false;
    if (!nextTokenIs(b, KW_REALIZES)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, KW_REALIZES);
    p = r; // pin = 1
    r = r && report_error_(b, contextRef(b, l + 1));
    r = p && realizesClause_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (COMMA contextRef)*
  private static boolean realizesClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "realizesClause_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!realizesClause_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "realizesClause_2", c)) break;
    }
    return true;
  }

  // COMMA contextRef
  private static boolean realizesClause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "realizesClause_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && contextRef(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KW_REFINES contextRef
  static boolean refinesClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "refinesClause")) return false;
    if (!nextTokenIs(b, KW_REFINES)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, KW_REFINES);
    p = r; // pin = 1
    r = r && contextRef(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // contextRef roleList? arrow roleList? contextRef
  public static boolean relationship(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "relationship")) return false;
    if (!nextTokenIs(b, "<relationship>", CONSTANT, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RELATIONSHIP, "<relationship>");
    r = contextRef(b, l + 1);
    r = r && relationship_1(b, l + 1);
    r = r && arrow(b, l + 1);
    r = r && relationship_3(b, l + 1);
    r = r && contextRef(b, l + 1);
    exit_section_(b, l, m, r, false, null);
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
  // CONSTANT | IDENTIFIER
  public static boolean role(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "role")) return false;
    if (!nextTokenIs(b, "<role>", CONSTANT, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ROLE, "<role>");
    r = consumeToken(b, CONSTANT);
    if (!r) r = consumeToken(b, IDENTIFIER);
    exit_section_(b, l, m, r, false, null);
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

  /* ********************************************************** */
  // KW_SUBDOMAIN name_ supportsClause? LBRACE bodyEntry_* RBRACE
  public static boolean subdomain(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subdomain")) return false;
    if (!nextTokenIs(b, KW_SUBDOMAIN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, SUBDOMAIN, null);
    r = consumeToken(b, KW_SUBDOMAIN);
    p = r; // pin = 1
    r = r && report_error_(b, name_(b, l + 1));
    r = p && report_error_(b, subdomain_2(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, LBRACE)) && r;
    r = p && report_error_(b, subdomain_4(b, l + 1)) && r;
    r = p && consumeToken(b, RBRACE) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // supportsClause?
  private static boolean subdomain_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subdomain_2")) return false;
    supportsClause(b, l + 1);
    return true;
  }

  // bodyEntry_*
  private static boolean subdomain_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "subdomain_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!bodyEntry_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "subdomain_4", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // KW_SUPPORTS name_ (COMMA name_)*
  static boolean supportsClause(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "supportsClause")) return false;
    if (!nextTokenIs(b, KW_SUPPORTS)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, KW_SUPPORTS);
    p = r; // pin = 1
    r = r && report_error_(b, name_(b, l + 1));
    r = p && supportsClause_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (COMMA name_)*
  private static boolean supportsClause_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "supportsClause_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!supportsClause_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "supportsClause_2", c)) break;
    }
    return true;
  }

  // COMMA name_
  private static boolean supportsClause_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "supportsClause_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && name_(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

}
