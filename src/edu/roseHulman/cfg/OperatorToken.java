/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

/**
 * This (manually constructed) enumeration represents the operators in CFGs.
 * 
 * @author cclifton
 */
public class OperatorToken extends Token {
	/**
	 * '::=' token
	 */
	public static final OperatorToken GOES_TO = new OperatorToken("::=");

	/**
	 * '|' token
	 */
	public static final OperatorToken OR = new OperatorToken("|");

	/**
	 * One or more newline characters with any intervening whitespace
	 */
	public static final OperatorToken NEWLINE = new OperatorToken("\\n");

	private final String tokenTest;

	private final int creationOrder;

	private static int nextCreationCount = 0;

	private OperatorToken(String tokenText) {
		this.tokenTest = tokenText;
		this.creationOrder = nextCreationCount++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.tokenTest;
	}

	@Override
	public int intraCategoryCompareTo(Token other) {
		return this.creationOrder - ((OperatorToken) other).creationOrder;
	}

	@Override
	public TokenCategory tokenCategory() {
		return TokenCategory.OPERATOR;
	}
}
