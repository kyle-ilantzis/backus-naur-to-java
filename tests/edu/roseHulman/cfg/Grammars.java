/*
 * Copyright � 2007�2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

import java.io.IOException;
import java.util.*;

import edu.roseHulman.cfg.CFGParser.SyntaxError;

/**
 * Defines the various test grammars as string constants.
 * 
 * @author cclifton
 * 
 */
public final class Grammars {

	private Grammars() {
		// not instantiable
	}

	/**
	 * 
	 * @param cfgGrammarString
	 * @return a Grammar for the CFG represented by the given input string
	 * @throws IOException
	 * @throws SyntaxError
	 */
	public static Grammar getGrammarFrom(String cfgGrammarString)
			throws IOException, SyntaxError {
		CFGParser cfgParser = new CFGParser(cfgGrammarString);
		cfgParser.parseGrammar();
		return cfgParser.grammar();
	}

	public static List<Token> tokensFromStrings( String ... strings ) {

		if ( strings == null || strings.length == 0 ) {
			return Collections.emptyList();
		} else {
			List<Token> ts = new ArrayList<>();
			for ( String s : Arrays.asList( strings) ) {
				ts.add( tokenFromString( s ) );
			}
			return ts;
		}
	}

	/**
	 * This helper method for the test cases constructs a token object from the
	 * given string.
	 * 
	 * @param string
	 * @return an appropriate token
	 */
	public static Token tokenFromString(String string) {
		if (string.equals("<<EOF>>")) {
			return EOFToken.getInstance();
		} else if (string.equals("e")) {
			return EmptyStringToken.getInstance();
		} else if (string.startsWith("<")) {
			return new NonTerminalToken(string);
		} else if (string.startsWith("{")) {
			return new ActionToken(string);
		}
		else {
			return new TerminalToken(string);
		}
	}

	public static List<List<Token>> flattenProductions( Grammar grammar ) {

		List<List<Token>> ps = new ArrayList<>( grammar.productions().size() );
		for ( Production p : grammar.productions() ) {
			ps.add( flattenProduction( p ) );
		}
		return ps;
	}

	public static List<Token> flattenProduction( Production production ) {

		List<Token> ts = new ArrayList<>( production.rightHandSide().size() + 1 );
		ts.add( production.leftHandSide() );
		ts.addAll( production.rightHandSide() );
		return ts;
	}
}
