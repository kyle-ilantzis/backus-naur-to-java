/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

/**
 * This is the type of tokens returned from the scanner. Tokens are comparable
 * so that they may be sorted for display purposes. Categories of tokens are
 * ordered as follows: NonTerminalToken less than OperatorToken less than
 * TerminalToken less than EmptyStringToken less than EOFToken. Ordering with
 * the categories is defined in the class for that category.
 * 
 * @author cclifton
 */
public abstract class Token implements Comparable<Token> {
	/**
	 * An enumeration providing quick type comparisons of tokens and giving a
	 * total order on token types.
	 */
	enum TokenCategory {
		/**
		 * Non-terminals in the grammar.
		 */
		NONTERMINAL,
		/**
		 * Operators in the grammar of CFGs.
		 */
		OPERATOR,
		/**
		 * Terminal symbols in the grammar of CFGs.
		 */
		TERMINAL,
		/**
		 * The empty string (singleton) token.
		 */
		EMPTY_STRING,
		/**
		 * The end-of-file token.
		 */
		EOF;
	}

	/**
	 * @return true if this token represents the end of the file
	 */
	public final boolean isEOF() {
		return this.tokenCategory() == TokenCategory.EOF;
	}

	/**
	 * @return true if this token is a non-terminal
	 */
	public final boolean isNonTerminal() {
		return this.tokenCategory() == TokenCategory.NONTERMINAL;
	}

	/**
	 * @return true if this token is a terminal
	 */
	public final boolean isTerminal() {
		return this.tokenCategory() == TokenCategory.TERMINAL;
	}

	/**
	 * @return true if this token is the empty string token
	 */
	public final boolean isEmptyString() {
		return this.tokenCategory() == TokenCategory.EMPTY_STRING;
	}

	/**
	 * The token category is a type flag indicating the type of the token and
	 * the position of that type in the total ordering of tokens.
	 * 
	 * @return the category of this token
	 */
	public abstract TokenCategory tokenCategory();

	public final int compareTo(Token o) {
		int firstOrderComparison = this.tokenCategory().compareTo(
				o.tokenCategory());
		if (firstOrderComparison == 0) {
			return this.intraCategoryCompareTo(o);
		}
		return firstOrderComparison;
	}

	/**
	 * Compares this token to the given token, returning -1 if this is less than
	 * the given token, 0 if this is equal to the given token, and 1 if this is
	 * greater than the given token. Clients must ensure that the type of other
	 * matches the type of this.
	 * 
	 * @param other
	 * @return result of the comparison
	 */
	protected abstract int intraCategoryCompareTo(Token other);

}
