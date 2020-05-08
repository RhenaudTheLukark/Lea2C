package fr.ubordeaux.deptinfo.compilation.lea.tokenizer;

import java.io.*;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import fr.ubordeaux.deptinfo.compilation.lea.parser.Symbols;

%%

%public
%class Tokenizer
%cupsym fr.ubordeaux.deptinfo.compilation.lea.parser.Symbols
%cup
%{
    private ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();

    private boolean log = true;
    
    private Symbol createSymbol(int id) {
    	if (log)
    		System.err.println("*** Token " + yytext() + " (" + Symbols.terminalNames[id] + ")");
    	return symbolFactory.newSymbol(yytext(), id, new Location(yyline+1, yycolumn +1), new Location(yyline+1, yycolumn+yylength())); 
    }

    private Symbol createSymbol(int id, Object value) {
    	if (log)
    		System.err.println("*** Token " + yytext() + " (" + Symbols.terminalNames[id] + ")");
    	return symbolFactory.newSymbol(yytext(), id, new Location(yyline+1, yycolumn +1), new Location(yyline+1, yycolumn+yylength()), value); 
    }
    
%}

%eofval{
    return createSymbol(Symbols.EOF);
%eofval}

%line

%column

space = [ \t]+
comment = ("//".* | "/*"~"*/")

%%

";"	{ return createSymbol(Symbols.SEMI); }
","	{ return createSymbol(Symbols.COMMA); }
":"	{ return createSymbol(Symbols.COLON); }
"."	{ return createSymbol(Symbols.DOT); }
"("	{ return createSymbol(Symbols.LPAR); }
")"	{ return createSymbol(Symbols.RPAR); }
"["	{ return createSymbol(Symbols.LBRACKET); }
"]"	{ return createSymbol(Symbols.RBRACKET); }
"{"	{ return createSymbol(Symbols.LBRACE); }
"}"	{ return createSymbol(Symbols.RBRACE); }
"+"	{ return createSymbol(Symbols.PLUS); }
"-"	{ return createSymbol(Symbols.MINUS); }
"*"	{ return createSymbol(Symbols.MUL); }
"/"	{ return createSymbol(Symbols.DIV); }
"%"	{ return createSymbol(Symbols.MOD); }
"="	{ return createSymbol(Symbols.EQUAL); }
"||"	{ return createSymbol(Symbols.OR); }
"&&"	{ return createSymbol(Symbols.AND); }
"!"	{ return createSymbol(Symbols.NOT); }
"<"	{ return createSymbol(Symbols.LT); }
"<="	{ return createSymbol(Symbols.LE); }
">"	{ return createSymbol(Symbols.GT); }
">="	{ return createSymbol(Symbols.GE); }
"!="	{ return createSymbol(Symbols.NE); }
"=="	{ return createSymbol(Symbols.EQ); }
"^"	{ return createSymbol(Symbols.CIRC); }
"@"	{ return createSymbol(Symbols.AROBASE); }

array	{ return createSymbol(Symbols.ARRAY); }
begin	{ return createSymbol(Symbols.BEGIN); }
boolean	{ return createSymbol(Symbols.BOOLEAN); }
case	{ return createSymbol(Symbols.CASE); }
const	{ return createSymbol(Symbols.CONST); }
default	{ return createSymbol(Symbols.DEFAULT); }
dispose	{ return createSymbol(Symbols.DISPOSE); }
else	{ return createSymbol(Symbols.ELSE); }
end		{ return createSymbol(Symbols.END); }
false	{ return createSymbol(Symbols.FALSE); }
float	{ return createSymbol(Symbols.FLOAT); }
function	{ return createSymbol(Symbols.FUNCTION); }
for	{ return createSymbol(Symbols.FOR); }
if	{ return createSymbol(Symbols.IF); }
integer	{ return createSymbol(Symbols.INTEGER); }
new		{ return createSymbol(Symbols.NEW); }
null		{ return createSymbol(Symbols.NULL); }
of		{ return createSymbol(Symbols.OF); }
print	{ return createSymbol(Symbols.PRINT); }
println	{ return createSymbol(Symbols.PRINTLN); }
procedure	{ return createSymbol(Symbols.PROCEDURE); }
readln		{ return createSymbol(Symbols.READLN); }
string		{ return createSymbol(Symbols.STRING); }
struct	{ return createSymbol(Symbols.STRUCT); }
switch	{ return createSymbol(Symbols.SWITCH); }
true		{ return createSymbol(Symbols.TRUE); }
type		{ return createSymbol(Symbols.TYPE); }
var			{ return createSymbol(Symbols.VAR); }
while		{ return createSymbol(Symbols.WHILE); }

[a-zA-Z_][a-zA-Z0-9_]*			{ return createSymbol(Symbols.IDENTIFIER, yytext()); }
[0-9]+							{ return createSymbol(Symbols.INTEGER_VALUE, new Integer(yytext())); }
[0-9]+(\.[0-9]+)?([eE][+-]?[0-9]+)?	{ return createSymbol(Symbols.FLOAT_VALUE, new Float(yytext())); }
\"~\"							{ return createSymbol(Symbols.STRING_VALUE, yytext()); }

{space}|\n						{}
{comment}						{}
[^]								{ System.out.println("non reconnu par le tokenizer: " + yytext()); }

