/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

/**
 * This immutable class represents a non-terminal token in a CFG.
 * 
 * @author cclifton
 */
public class NonTerminalToken extends Token {

	private final String text;

	/**
	 * Creates a non-terminal token with the given text
	 * 
	 * @param text
	 */
	public NonTerminalToken(String text) {
		this.text = text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof NonTerminalToken) {
			NonTerminalToken otherToken = (NonTerminalToken) other;
			return this.text.equals(otherToken.text);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.text.hashCode();
	}

	@Override
	public TokenCategory tokenCategory() {
		return TokenCategory.NONTERMINAL;
	}

	@Override
	public int intraCategoryCompareTo(Token other) {
		return this.text.compareTo(((NonTerminalToken) other).text);
	}
}
