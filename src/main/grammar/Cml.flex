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
CONSTANT=[A-Z][A-Z0-9_]*
STRING=\"[^\"]*\"
NUMBER=[0-9]+(\.[0-9]+)?
WS=[\ \t]+

%%

<YYINITIAL> {
  {WHITE_SPACE}      { return TokenType.WHITE_SPACE; }
  {LINE_COMMENT}     { return CmlTypes.LINE_COMMENT; }
  {BLOCK_COMMENT}    { return CmlTypes.BLOCK_COMMENT; }

  "ContextMap"       { return CmlTypes.KW_CONTEXT_MAP; }
  "BoundedContext"   { return CmlTypes.KW_BOUNDED_CONTEXT; }
  "Domain"           { return CmlTypes.KW_DOMAIN; }
  "Subdomain"        { return CmlTypes.KW_SUBDOMAIN; }
  "contains"         { return CmlTypes.KW_CONTAINS; }
  "implements"       { return CmlTypes.KW_IMPLEMENTS; }
  "refines"          { return CmlTypes.KW_REFINES; }
  "realizes"         { return CmlTypes.KW_REALIZES; }
  "supports"         { return CmlTypes.KW_SUPPORTS; }

  "Aggregate"           { return CmlTypes.DECL_KEYWORD; }
  "Module"              { return CmlTypes.DECL_KEYWORD; }
  "Entity"              { return CmlTypes.DECL_KEYWORD; }
  "ValueObject"         { return CmlTypes.DECL_KEYWORD; }
  "DomainEvent"         { return CmlTypes.DECL_KEYWORD; }
  "CommandEvent"        { return CmlTypes.DECL_KEYWORD; }
  "Event"               { return CmlTypes.DECL_KEYWORD; }
  "Command"             { return CmlTypes.DECL_KEYWORD; }
  "Service"             { return CmlTypes.DECL_KEYWORD; }
  "Repository"          { return CmlTypes.DECL_KEYWORD; }
  "Resource"            { return CmlTypes.DECL_KEYWORD; }
  "Consumer"            { return CmlTypes.DECL_KEYWORD; }
  "Trait"               { return CmlTypes.DECL_KEYWORD; }
  "BasicType"           { return CmlTypes.DECL_KEYWORD; }
  "DataTransferObject"  { return CmlTypes.DECL_KEYWORD; }
  "Application"         { return CmlTypes.DECL_KEYWORD; }
  "Flow"                { return CmlTypes.DECL_KEYWORD; }
  "Coordination"        { return CmlTypes.DECL_KEYWORD; }
  "UseCase"             { return CmlTypes.DECL_KEYWORD; }
  "UserStory"           { return CmlTypes.DECL_KEYWORD; }
  "Enum"                { return CmlTypes.DECL_KEYWORD; }
  "enum"                { return CmlTypes.DECL_KEYWORD; }

  "type"                    { return CmlTypes.PROP_KEYWORD; }
  "state"                   { return CmlTypes.PROP_KEYWORD; }
  "domainVisionStatement"   { return CmlTypes.PROP_KEYWORD; }
  "implementationTechnology" { return CmlTypes.PROP_KEYWORD; }
  "responsibilities"        { return CmlTypes.PROP_KEYWORD; }
  "knowledgeLevel"          { return CmlTypes.PROP_KEYWORD; }
  "businessModel"           { return CmlTypes.PROP_KEYWORD; }
  "evolution"               { return CmlTypes.PROP_KEYWORD; }
  "downstreamRights"        { return CmlTypes.PROP_KEYWORD; }
  "exposedAggregates"       { return CmlTypes.PROP_KEYWORD; }
  "owner"                   { return CmlTypes.PROP_KEYWORD; }
  "useCases"                { return CmlTypes.PROP_KEYWORD; }
  "userStories"             { return CmlTypes.PROP_KEYWORD; }
  "features"                { return CmlTypes.PROP_KEYWORD; }
  "securityZone"            { return CmlTypes.PROP_KEYWORD; }
  "securityAccessGroup"     { return CmlTypes.PROP_KEYWORD; }
  "contentVolatility"       { return CmlTypes.PROP_KEYWORD; }
  "likelihoodForChange"     { return CmlTypes.PROP_KEYWORD; }
  "structuralVolatility"    { return CmlTypes.PROP_KEYWORD; }
  "availabilityCriticality" { return CmlTypes.PROP_KEYWORD; }
  "consistencyCriticality"  { return CmlTypes.PROP_KEYWORD; }
  "storageSimilarity"       { return CmlTypes.PROP_KEYWORD; }
  "securityCriticality"     { return CmlTypes.PROP_KEYWORD; }
  "actor"                   { return CmlTypes.PROP_KEYWORD; }
  "secondaryActors"         { return CmlTypes.PROP_KEYWORD; }
  "interactions"            { return CmlTypes.PROP_KEYWORD; }
  "benefit"                 { return CmlTypes.PROP_KEYWORD; }
  "scope"                   { return CmlTypes.PROP_KEYWORD; }
  "level"                   { return CmlTypes.PROP_KEYWORD; }
  "aggregateRoot"           { return CmlTypes.PROP_KEYWORD; }
  "aggregateLifecycle"      { return CmlTypes.PROP_KEYWORD; }
  "read-only"               { return CmlTypes.PROP_KEYWORD; }
  "write"                   { return CmlTypes.PROP_KEYWORD; }
  "abstract"                { return CmlTypes.PROP_KEYWORD; }
  "extends"                 { return CmlTypes.PROP_KEYWORD; }
  "throws"                  { return CmlTypes.PROP_KEYWORD; }
  "nullable"                { return CmlTypes.PROP_KEYWORD; }
  "required"                { return CmlTypes.PROP_KEYWORD; }
  "def"                     { return CmlTypes.PROP_KEYWORD; }
  "key"                     { return CmlTypes.PROP_KEYWORD; }
  "not"                     { return CmlTypes.PROP_KEYWORD; }

  "delegates"{WS}"to"       { return CmlTypes.FLOW_KEYWORD; }
  "initiated"{WS}"by"       { return CmlTypes.FLOW_KEYWORD; }
  "emits"                   { return CmlTypes.FLOW_KEYWORD; }
  "triggers"                { return CmlTypes.FLOW_KEYWORD; }
  "event"                   { return CmlTypes.FLOW_KEYWORD; }
  "command"                 { return CmlTypes.FLOW_KEYWORD; }
  "operation"               { return CmlTypes.FLOW_KEYWORD; }

  "As"{WS}"an"              { return CmlTypes.STORY_KEYWORD; }
  "As"{WS}"a"               { return CmlTypes.STORY_KEYWORD; }
  "I"{WS}"want"{WS}"to"     { return CmlTypes.STORY_KEYWORD; }
  "so"{WS}"that"            { return CmlTypes.STORY_KEYWORD; }
  "and"{WS}"that"           { return CmlTypes.STORY_KEYWORD; }
  "accepting"{WS}"that"     { return CmlTypes.STORY_KEYWORD; }
  "with"{WS}"its"           { return CmlTypes.STORY_KEYWORD; }
  "for"{WS}"an"             { return CmlTypes.STORY_KEYWORD; }
  "for"{WS}"a"              { return CmlTypes.STORY_KEYWORD; }
  "in"{WS}"an"              { return CmlTypes.STORY_KEYWORD; }
  "in"{WS}"a"               { return CmlTypes.STORY_KEYWORD; }
  "is"{WS}"promoted"        { return CmlTypes.STORY_KEYWORD; }
  "are"{WS}"promoted"       { return CmlTypes.STORY_KEYWORD; }
  "is"{WS}"harmed"          { return CmlTypes.STORY_KEYWORD; }
  "are"{WS}"harmed"         { return CmlTypes.STORY_KEYWORD; }
  "create"                  { return CmlTypes.STORY_KEYWORD; }
  "read"                    { return CmlTypes.STORY_KEYWORD; }
  "update"                  { return CmlTypes.STORY_KEYWORD; }
  "delete"                  { return CmlTypes.STORY_KEYWORD; }

  "String"      { return CmlTypes.PRIMITIVE; }
  "int"         { return CmlTypes.PRIMITIVE; }
  "long"        { return CmlTypes.PRIMITIVE; }
  "short"       { return CmlTypes.PRIMITIVE; }
  "byte"        { return CmlTypes.PRIMITIVE; }
  "char"        { return CmlTypes.PRIMITIVE; }
  "boolean"     { return CmlTypes.PRIMITIVE; }
  "double"      { return CmlTypes.PRIMITIVE; }
  "float"       { return CmlTypes.PRIMITIVE; }
  "void"        { return CmlTypes.PRIMITIVE; }
  "Date"        { return CmlTypes.PRIMITIVE; }
  "DateTime"    { return CmlTypes.PRIMITIVE; }
  "Timestamp"   { return CmlTypes.PRIMITIVE; }
  "BigDecimal"  { return CmlTypes.PRIMITIVE; }
  "BigInteger"  { return CmlTypes.PRIMITIVE; }
  "Blob"        { return CmlTypes.PRIMITIVE; }
  "Clob"        { return CmlTypes.PRIMITIVE; }
  "Object"      { return CmlTypes.PRIMITIVE; }
  "List"        { return CmlTypes.PRIMITIVE; }
  "Set"         { return CmlTypes.PRIMITIVE; }
  "Bag"         { return CmlTypes.PRIMITIVE; }
  "Map"         { return CmlTypes.PRIMITIVE; }

  "<->"              { return CmlTypes.BIARROW; }
  "->"               { return CmlTypes.RARROW; }
  "<-"               { return CmlTypes.LARROW; }
  "::"               { return CmlTypes.DCOLON; }

  "{"                { return CmlTypes.LBRACE; }
  "}"                { return CmlTypes.RBRACE; }
  "["                { return CmlTypes.LBRACKET; }
  "]"                { return CmlTypes.RBRACKET; }
  "("                { return CmlTypes.LPAREN; }
  ")"                { return CmlTypes.RPAREN; }
  "<"                { return CmlTypes.LANGLE; }
  ">"                { return CmlTypes.RANGLE; }
  "="                { return CmlTypes.EQ; }
  ","                { return CmlTypes.COMMA; }
  ";"                { return CmlTypes.SEMI; }
  ":"                { return CmlTypes.COLON; }
  "-"                { return CmlTypes.MINUS; }
  "@"                { return CmlTypes.AT; }
  "+"                { return CmlTypes.PLUS; }
  "*"                { return CmlTypes.STAR; }

  {STRING}           { return CmlTypes.STRING; }
  {NUMBER}           { return CmlTypes.NUMBER; }
  {CONSTANT}         { return CmlTypes.CONSTANT; }
  {IDENTIFIER}       { return CmlTypes.IDENTIFIER; }
}

[^] { return TokenType.BAD_CHARACTER; }
