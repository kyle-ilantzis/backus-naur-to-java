/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This immutable class represents the first sets for all the symbols appearing
 * in a grammar. It includes facilities for calculating the first sets from a
 * grammar and for taking First of a sequence of symbols.
 * 
 * @author cclifton
 */
public class FirstSets {

	/*
	 * Uses sorted sets for the domain to simplify test. Sorting isn't
	 * practically required.
	 */
	private final Map<Token, SortedSet<Token>> firstMap;

	private final NullableNonterminals nullable;

	/**
	 * Constructs a new first sets map for the given grammar with the given
	 * Nullable set.
	 * 
	 * @param grammar
	 * @param nullable
	 */
	public FirstSets(Grammar grammar, NullableNonterminals nullable) {
		super();
		this.nullable = nullable;

		// Uses sorted map and sorted set to simplifying testing.
		SortedMap<Token, SortedSet<Token>> firstMap = new TreeMap<Token, SortedSet<Token>>();

		/*
		 * TODO 02: Populate the First Sets (as stored in firstMap). To create
		 * the SortedSets used as values in the map, you should construct new
		 * TreeSet<Token>().
		 */

		// ---------------------------------------------------------------------
		/*
		 * Locks down the sets in the range of the map so that they aren't
		 * mutated. Once the first sets are calculated above they should never
		 * change.
		 */
		this.firstMap = NestedCollectionLocker.unmodifiableMap(firstMap);
	}

	/**
	 * @return an immutable map representation of this
	 */
	public Map<Token, SortedSet<Token>> getMap() {
		return this.firstMap;
	}

	/**
	 * Calculates the First set of the given sequence of symbols.
	 * 
	 * @param symbols
	 * @return the set of tokens in the first set of the given sequence of
	 *         symbols
	 */
	public Set<Token> first(List<Token> symbols) {
		TreeSet<Token> result = new TreeSet<Token>();
		for (Token sym : symbols) {
			final Set<Token> firstOfSym = firstMap.get(sym);
			if (firstOfSym != null) {
				result.addAll(firstOfSym);
				result.remove(EmptyStringToken.getInstance());
			}
			if (!nullable.isNullableToken(sym)) {
				return result;
			}
		}
		result.add(EmptyStringToken.getInstance());
		return result;
	}

	/**
	 * Calculates the First+ set of the given sequence of symbols.
	 * 
	 * This calculation uses both the First and Follow sets of the grammar. It
	 * could reasonably be put in either class, but most of the work happens
	 * here.
	 * 
	 * @param symbols
	 * @param followSets
	 *            the follow sets of the same grammar from which this first set
	 *            was formed.
	 * @return the set of tokens in the first+ set of the given sequence of
	 *         symbols
	 */
	public Set<Token> firstPlus(List<Token> symbols, FollowSets followSets) {
		SortedSet<Token> result = (SortedSet<Token>) this.first(symbols);
		if (result.contains(EmptyStringToken.getInstance())) {
			result.addAll(followSets.follow(symbols));
		}
		return result;
	}

	@Override
	public String toString() {
		return "First sets: " + this.firstMap.toString();
	}
}
