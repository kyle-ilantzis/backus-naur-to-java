/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

import java.util.List;

/**
 * This immutable type represents a production mapping a non-terminal to a
 * sequence of symbols.
 * 
 * @author cclifton
 * @author kelleybt
 */
public interface Production extends Comparable<Production> {
	/**
	 * @return true if this is the goal production of the grammar
	 */
	public boolean isGoalProduction();

	/**
	 * @return the list of tokens representing the right-hand side of this
	 *         production
	 */
	public List<Token> rightHandSide();

	/**
	 * @return the non-terminal tokens representing the left-hand side of this
	 *         production
	 */
	public NonTerminalToken leftHandSide();

	/**
	 * @return true if the right hand side is the empty string, otherwise
	 *         false
	 */
	public boolean goesToEpsilon();
	
	/**
	 * @return the grammar to which this production belongs
	 */
	public Grammar grammar();
	
	/**
	 * @return the index of this production within the grammar
	 */
	public int productionNumber();

}
