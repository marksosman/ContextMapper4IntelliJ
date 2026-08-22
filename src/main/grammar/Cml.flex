package io.github.marksosman.dddcontextmapper;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import io.github.marksosman.dddcontextmapper.psi.CmlTypes;

%%

%class _CmlLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

WHITE_SPACE=[\ \t\f\r\n]+
LINE_COMMENT="//"[^\r\n]*
BLOCK_COMMENT="/"\*([^*]|\*+[^*/])*\*+"/"
IDENTIFIER=[A-Za-z_][A-Za-z0-9_]*
STRING=\"[^\"]*\"
NUMBER=[0-9]+(\.[0-9]+)?

%%

<YYINITIAL> {
  {WHITE_SPACE}      { return TokenType.WHITE_SPACE; }
  {LINE_COMMENT}     { return CmlTypes.LINE_COMMENT; }
  {BLOCK_COMMENT}    { return CmlTypes.BLOCK_COMMENT; }

  "ContextMap"       { return CmlTypes.KW_CONTEXT_MAP; }
  "BoundedContext"   { return CmlTypes.KW_BOUNDED_CONTEXT; }
  "contains"         { return CmlTypes.KW_CONTAINS; }

  "<->"              { return CmlTypes.BIARROW; }
  "->"               { return CmlTypes.RARROW; }
  "<-"               { return CmlTypes.LARROW; }

  "{"                { return CmlTypes.LBRACE; }
  "}"                { return CmlTypes.RBRACE; }
  "["                { return CmlTypes.LBRACKET; }
  "]"                { return CmlTypes.RBRACKET; }
  "="                { return CmlTypes.EQ; }
  ","                { return CmlTypes.COMMA; }

  {STRING}           { return CmlTypes.STRING; }
  {NUMBER}           { return CmlTypes.NUMBER; }
  {IDENTIFIER}       { return CmlTypes.IDENTIFIER; }
}

[^] { return TokenType.BAD_CHARACTER; }
