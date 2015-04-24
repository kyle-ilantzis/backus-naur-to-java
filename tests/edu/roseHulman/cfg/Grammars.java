/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.roseHulman.cfg.CFGParser.SyntaxError;

/**
 * Defines the various test grammars as string constants.
 * 
 * @author cclifton
 * 
 */
public final class Grammars {
	/**
	 * Only nothing is acceptable!
	 */
	public static final String NothingButNothing = "<Start> ::= e" + "\n";

	/** baa */
	public static final String SheepNoise = "<S>	::=	baa <S>" + '\n' + "	|	e"
			+ '\n';

	/** + and - with left-recursion removed */
	public static final String Expression1 = "<Expr> ::= <Term> <Expr'>" + '\n'
			+ "" + '\n' + "<Expr'> ::= + <Term> <Expr'>" + '\n'
			+ "        |  - <Term> <Expr'>" + '\n' + "        |  e" + '\n' + ""
			+ '\n' + "<Term> ::= num" + '\n';

	/** * and / with left-recursion removed */
	public static final String Quiz7 = "<Term> ::= <Factor> <Term'>" + '\n'
			+ "" + '\n' + "<Term'> ::= * <Factor> <Term'>" + '\n'
			+ "        |  / <Factor> <Term'>" + '\n' + "        |  e" + '\n'
			+ "" + '\n' + "<Factor> ::= num" + '\n';

	/** block statements and null */
	public static final String Quiz15 = "<Stmt> 	::= { <Stmt> }" + '\n'
			+ "	|    null;" + '\n';

	/** parens */
	public static final String Parens = "<Parens> ::= ( <Parens> )" + '\n'
			+ "	| <Parens> <Parens>" + '\n' + "	| x" + '\n';

	/** if-then-else with ambiguity */
	public static final String IfThenElse1 = "<Stmt> 	::= if expr then <Stmt> else <Stmt>"
			+ '\n' + "	|    if expr then <Stmt>" + '\n' + "	|    assign" + '\n';

	/** if-then-else without ambiguity */
	public static final String IfThenElse1withoutAmbig = "<Stmt> 	::= if expr then <WithElse> else <Stmt>"
			+ '\n'
			+ "	|    if expr then <Stmt>"
			+ '\n'
			+ "	|    assn"
			+ '\n'
			+ ""
			+ '\n'
			+ "<WithElse>	::= if expr then <WithElse> else <WithElse>"
			+ '\n'
			+ "	|	assn" + '\n';

	/** if-then-else without ambiguity, left factored */
	public static final String IfThenElse1leftFactored = "<Stmt> 	::= if expr then <IfRest>"
			+ '\n'
			+ "	|    assn"
			+ '\n'
			+ ""
			+ '\n'
			+ "<IfRest> 	::= <WithElse> else <Stmt>"
			+ '\n'
			+ "	|    <Stmt>"
			+ '\n'
			+ ""
			+ '\n'
			+ "<WithElse>	::= if expr then <WithElse> else <WithElse>"
			+ '\n'
			+ "	|	assn" + '\n';

	/** if-then-else with block statements */
	public static final String IfThenElse2 = "<Stmt> ::= if <Expr> then <Stmt>"
			+ '\n' + "	| if <Expr> then <WithElse> else <Stmt>" + '\n'
			+ "	| <Assn>" + '\n' + "" + '\n'
			+ "<WithElse> ::= if <Expr> then <WithElse> else <WithElse>" + '\n'
			+ "	| <Assn>" + '\n' + "" + '\n' + "<Assn> ::= assn" + '\n'
			+ "	| lcb <Stmt> rcb" + '\n' + "" + '\n' + "<Expr> ::= true" + '\n'
			+ "	| false" + '\n' + "";

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
		} else {
			return new TerminalToken(string);
		}
	}

	private static Set<Token> ensureSetForKey(Map<Token, Set<Token>> map,
			Token key) {
		Set<Token> result = map.get(key);
		if (result == null) {
			result = new HashSet<Token>();
			map.put(key, result);
		}
		return result;
	}

	/**
	 * Builds a mapping from tokens to sets of tokens.
	 * 
	 * @param mappings
	 *            contains strings like:
	 * 
	 *            "foo|bar" to indicate a mapping from token foo to token bar
	 *            should exist
	 * 
	 *            and
	 * 
	 *            "baz" to indicate that token baz should map to the empty set
	 * @return a new map
	 */
	public static Map<Token, Set<Token>> buildExpectedMap(String[] mappings) {
		HashMap<Token, Set<Token>> result = new HashMap<Token, Set<Token>>();
		for (String s : mappings) {
			String[] pair = s.split("\\|");
			Token key = tokenFromString(pair[0]);
			if (pair.length == 1) {
				// a "pair" without a second element is used to signify an empty
				// result set
				ensureSetForKey(result, key);
			} else {
				Set<Token> value = ensureSetForKey(result, key);
				value.add(tokenFromString(pair[1]));
			}
		}
		return result;
	}

	/**
	 * @param expectedElements
	 * @return the same as Arrays.toString(expectedElements) except leading and
	 *         trailing square brackets are replaced with curly braces
	 */
	public static String toStringWithCurlies(String[] expectedElements) {
		String expected = Arrays.toString(expectedElements);
		expected = "{" + expected.substring(1, expected.length() - 1) + "}";
		return expected;
	}
}
