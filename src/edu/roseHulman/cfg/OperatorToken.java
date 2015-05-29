/*
 * Copyright � 2007�2010, Curtis Clifton and Brian T. Kelley
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
	public static final OperatorToken GOES_TO = new OperatorToken("::=", "::=");

	/**
	 * '|' token
	 */
	public static final OperatorToken OR = new OperatorToken("|", "|");

	/**
	 * One or more newline characters with any intervening whitespace
	 */
	public static final OperatorToken NEWLINE = new OperatorToken("\\n", "newline");

	private final String tokenText;

	private final String prettyText;

	private final int creationOrder;

	private static int nextCreationCount = 0;

	private OperatorToken(String tokenText, String prettyText ) {
		this.tokenText = tokenText;
		this.prettyText = prettyText;
		this.creationOrder = nextCreationCount++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.tokenText;
	}

	/**
	 * @return The appropriate text to display to a user for messages and the like.
	 */
	public String getPrettyText() {
		return prettyText;
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
