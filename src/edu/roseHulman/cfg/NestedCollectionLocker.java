/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

/**
 * This utility class provides methods for creating deeply immutable data
 * structures.
 * 
 * @author cclifton
 * 
 */
public class NestedCollectionLocker {

	/**
	 * Returns a deeply immutable, sorted map otherwise identical to the
	 * argument.
	 * @param <K> the type of keys
	 * @param <S> the type of elements in the sets of the range
	 * 
	 * @param map
	 * @return a deeply immutable, sorted map otherwise identical to the
	 *         argument
	 */
	public static <K,S> SortedMap<K, SortedSet<S>> unmodifiableMap(
			SortedMap<K, SortedSet<S>> map) {
		TreeMap<K, SortedSet<S>> mapWithImmutableRangeSets = new TreeMap<K, SortedSet<S>>();
		for (Map.Entry<K, SortedSet<S>> entry : map.entrySet()) {
			mapWithImmutableRangeSets.put(entry.getKey(), Collections
					.unmodifiableSortedSet(entry.getValue()));
		}
		return Collections.unmodifiableSortedMap(mapWithImmutableRangeSets);
	}

}
