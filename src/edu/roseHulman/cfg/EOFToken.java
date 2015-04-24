/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

/**
 * A token to represent end-of-file.
 * 
 * @author cclifton
 */
public class EOFToken extends Token {

	private static Token theInstance = new EOFToken();

	private EOFToken() {
		// No-op constructor enforces singleton-ness
	}

	/**
	 * @return a singleton instance of the EOFToken
	 */
	public static Token getInstance() {
		return theInstance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "<<EOF>>";
	}

	@Override
	public int intraCategoryCompareTo(Token other) {
		return 0;
	}

	@Override
	public TokenCategory tokenCategory() {
		return TokenCategory.EOF;
	}

}
