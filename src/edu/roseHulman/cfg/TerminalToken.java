/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;


/**
 * This immutable class represents terminal tokens in a CFG.
 * @author cclifton
 */
public class TerminalToken extends Token {

	private final String text;

	/**
	 * Constructs a new terminal token with the given text
	 * @param text
	 */
	public TerminalToken(String text) {
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.text;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (other instanceof TerminalToken) {
			TerminalToken otherToken = (TerminalToken) other;
			return this.text.equals(otherToken.text);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.text.hashCode();
	}

	@Override
	public int intraCategoryCompareTo(Token other) {
		return this.text.compareTo(((TerminalToken) other).text);
	}

	@Override
	public TokenCategory tokenCategory() {
		return TokenCategory.TERMINAL;
	}
}
