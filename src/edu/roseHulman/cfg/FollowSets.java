/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This immutable class represents the follows sets for a grammar.
 * 
 * @author cclifton
 */
public class FollowSets {

	/*
	 * Uses sorted sets for the domain to simplify test. Sorting isn't
	 * practically required.
	 */
	private final Map<Token, SortedSet<Token>> followMap;
	private final NullableNonterminals nullable;
	private final FirstSets firstSets;

	/**
	 * Constructs the follow sets for the given grammar with the given nullable
	 * and first sets.
	 * 
	 * @param grammar
	 * @param nullable
	 * @param firstSets
	 */
	public FollowSets(Grammar grammar, NullableNonterminals nullable,
			FirstSets firstSets) {
		super();
		this.nullable = nullable;
		this.firstSets = firstSets;

		// Uses sorted map and sorted set to simplifying testing.
		SortedMap<Token, SortedSet<Token>> followMap = new TreeMap<Token, SortedSet<Token>>();

		/*
		 * TODO 03: Populate the Follow Sets (as stored in followMap). To create
		 * the SortedSets used as values in the map, you should construct new
		 * TreeSet<Token>().
		 * 
		 * NOTE: In the first edition of Engineering a Compiler, the Follow set
		 * algorithm in Figure 3.5 has an error.  The 'if' in that algorithm (in line
		 * 6 from the end) should have condition "epsilon in First(Beta_1)", not
		 * Follow as printed.
		 */

		// ---------------------------------------------------------------------
		/*
		 * Locks down the sets in the range of the map so that they aren't
		 * mutated. Once the follow sets are calculated above they should never
		 * change.
		 */
		this.followMap = NestedCollectionLocker.unmodifiableMap(followMap);
	}

	/**
	 * @return an immutable map representation of this
	 */
	public Map<Token, SortedSet<Token>> getMap() {
		return this.followMap;
	}

	@Override
	public String toString() {
		return "Follow sets: " + this.followMap.toString();
	}

	/**
	 * Calculates the follow set of the given symbol.
	 * 
	 * @param symbol
	 * @return the follow set of the given symbol
	 */
	public Set<Token> follow(Token symbol) {
		return this.followMap.get(symbol);
	}

	/**
	 * Calculates the follow set of the given sequence of symbol.
	 * 
	 * @param symbols
	 * @return the follow set of the given sequence of symbols
	 */
	public Set<Token> follow(List<Token> symbols) {
		// Engineering a Compiler doesn't give the algorithm for this.
		// TODO Auto-generated method stub
		TreeSet<Token> result = new TreeSet<Token>();
		ListIterator<Token> iter = symbols.listIterator(symbols.size());
		while (iter.hasPrevious()) {
			Token t = iter.previous();
			if (!t.isNonTerminal()) {
				break;
			}
			result.addAll(follow(t));
			if (!this.nullable.isNullableToken(t)) {
				break;
			}
		}
		return result;
	}
}
