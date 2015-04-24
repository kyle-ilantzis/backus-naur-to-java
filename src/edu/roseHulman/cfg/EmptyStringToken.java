/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

/**
 * This class represents an epsilon -- empty string -- in a CFG.
 * @author cclifton
 */
public class EmptyStringToken extends Token {

	private static Token theInstance = new EmptyStringToken();

	private EmptyStringToken() {
		// No-op constructor to enforce singleton-ness
	}
	
	/**
	 * @return a singleton EmptyStringToken
	 */
	public static Token getInstance() {
		return theInstance ;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "e";
	}

	@Override
	public int intraCategoryCompareTo(Token other) {
		return 0;
	}

	@Override
	public TokenCategory tokenCategory() {
		return TokenCategory.EMPTY_STRING;
	}
}
