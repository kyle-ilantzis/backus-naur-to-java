/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * This immutable class represents the nullable non-terminals for a grammar. It
 * includes facilities for calculating what is nullable given a grammar.
 * 
 * @author cclifton
 */
public class NullableNonterminals {

	private static final long serialVersionUID = 1L;

	// Uses sorted set to simplify testing.
	private SortedSet<Token> nullable = new TreeSet<Token>();;

	/**
	 * Constructs a nullable set for the given grammar.
	 * 
	 * @param g
	 */
	public NullableNonterminals(Grammar g) {
		super();

		// TODO 01: Construct Nullable Nonterminals

		// ---------------------------------------------------------------------
		/*
		 * Stores an immutable version of the non-terminal set to avoid
		 * accidentally mutating it later.
		 */
		this.nullable = Collections.unmodifiableSortedSet(nullable);
	}

	/**
	 * @return an immutable set representation of this
	 */
	public Set<Token> getSet() {
		return this.nullable;
	}

	/**
	 * @param t
	 * @return true if the given token is nullable
	 */
	public boolean isNullableToken(Token t) {
		return t == EmptyStringToken.getInstance() || this.nullable.contains(t);
	}

	/**
	 * @param prod
	 * @return true if every symbol in the rhs of the given production is in
	 *         this
	 */
	private boolean isNullableProduction(Production prod) {
		return isNullable(prod.rightHandSide());
	}

	/**
	 * @param symbols
	 * @return true if every one of the given list of symbols is nullable
	 */
	public boolean isNullable(List<Token> symbols) {
		for (Token token : symbols) {
			if (!isNullableToken(token)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Nullable: " + this.nullable.toString();
	}

}
