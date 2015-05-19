/*
 * Copyright � 2007�2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

/***************************************************
 * Lexical grammar for CFGs 
 * by Curt Clifton
 * Rose-Hulman Institute of Technology
 * Copyright (C) 2006-07
 ***************************************************/
package edu.roseHulman.cfg;

// --------------------------------------------------
// User Code
// --------------------------------------------------

%%
// --------------------------------------------------
// Options and Declarations
// --------------------------------------------------

//%debug
%class Scanner
%apiprivate
%type edu.roseHulman.cfg.Token

%unicode
%line
%column

%{
	/**
	 * Nicely named interface to the lexer.
	 */
	public Token nextToken() throws java.io.IOException {
		return yylex();
	}
%}

// Probably need to include whitespace between newlines
Whitespace = (\b|\t|\f|" ")*  // This probably isn't unicode
Newline = (\r|\n|\u2028|\u2029|\u000B|\u000C|\u0085)+
Comment = ("/*" ~"*/")
Identifier = [:jletter:] ( [:jletterdigit:] | "'" )*
Terminal = [^\r\n\u2028\u2029\u000B\u000C\u0085\b\t\f ]+

%%
// --------------------------------------------------
// Lexical Rules
// --------------------------------------------------

<YYINITIAL> {
	// keywords
	"e"						{ return EmptyStringToken.getInstance(); }
	
	// operators
	"::="					{ return OperatorToken.GOES_TO; }
	"|"						{ return OperatorToken.OR; }
	({Newline} {Whitespace}*)+	{ return OperatorToken.NEWLINE; }
	
	// non-terminals
	"<" {Identifier} ">"		{ return new NonTerminalToken(yytext()); }
	
	// terminals
	{Terminal}				{ return new TerminalToken(yytext()); }
	
	// to ignore
	{Comment}				{ /* ignore */ }
	{Whitespace}				{ /* ignore */ }
	
	.						{ System.err.println("unexpected character at line " + (yyline + 1) + " column " + yycolumn + ": " + yytext()); }
	
}

// eof
<YYINITIAL>	<<EOF>>			{ return EOFToken.getInstance(); }

