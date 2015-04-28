/*
 * Copyright � 2007�2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This class represents a context-free grammar. The grammar is represented as a
 * collection of productions. Client code can add productions to the grammar.
 * Once all the productions have been added, a client should call
 * {@link #finalizeGrammar()}. This triggers an analysis of the grammar and
 * makes the grammar immutable.
 * 
 * @author cclifton
 * @author kelleybt
 */
public class Grammar {
	/**
	 * To ensure a unique production with left-hand side &lt;Goal&gt;, the
	 * system assumes the existence of a single Goal non-terminal that derives
	 * the first non-terminal in the user's grammar.
	 */
	public static final NonTerminalToken GOAL_SYMBOL = new NonTerminalToken(
			"<Goal>");

	private LinkedList<Production> productions;

	private Set<NonTerminalToken> nonTerminals = new TreeSet<NonTerminalToken>();

	private Set<TerminalToken> terminals = new TreeSet<TerminalToken>();

	private Set<ActionToken> actions = new TreeSet<ActionToken>();

	private boolean goalProductionAdded = false;

	private NullableNonterminals nullableNonterminals;

	private FirstSets firstSets;

	private FollowSets followSets;

	private int nextProductionNumber = 1; // 0 is reserved for GoalProduction

	/**
	 * This immutable type represents a production mapping a non-terminal to a
	 * sequence of symbols.
	 */
	private class ProductionImpl implements Production {

		/**
		 * The left-hand side of this production.
		 */
		final NonTerminalToken leftHandSide;

		/**
		 * The right-hand side of this production.
		 */
		final List<Token> rightHandSide;

		private final int productionNumber;

		/**
		 * Constructs a production with the given left- and right-hand sides.
		 * 
		 * @param lhs
		 * @param rhs
		 */
		public ProductionImpl(NonTerminalToken lhs, List<Token> rhs) {
			this.leftHandSide = lhs;
			this.rightHandSide = Collections.unmodifiableList(rhs);
			this.productionNumber = nextProductionNumber++;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuffer result = new StringBuffer();
			result.append(leftHandSide.toString());
			result.append(" ::=");
			for (Token t : rightHandSide) {
				result.append(' ');
				result.append(t.toString());
			}
			return result.toString();
		}

		public boolean isGoalProduction() {
			return false;
		}

		public int compareTo(Production other) {
			if (this.grammar() != other.grammar()) {
				throw new IllegalArgumentException(
						"Productions from different grammars are incomparable");
			}
			return this.productionNumber() - other.productionNumber();
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Production)) {
				return false;
			}
			Production other = (Production) obj;
			if (this.grammar() != other.grammar()) {
				throw new IllegalArgumentException(
						"Productions from different grammars are incomparable");
			}
			return this.productionNumber() == other.productionNumber();

		}

		/**
		 * @return the ordinal number of this production
		 */
		public int productionNumber() {
			return this.productionNumber;
		}

		public boolean goesToEpsilon() {
			return this.rightHandSide.size() == 1
					&& this.rightHandSide.get(0).isEmptyString();
		}

		public NonTerminalToken leftHandSide() {
			return this.leftHandSide;
		}

		public List<Token> rightHandSide() {
			return Collections.unmodifiableList(this.rightHandSide);
		}

		@Override
		public Grammar grammar() {
			return Grammar.this;
		}
	}

	/**
	 * This immutable class represents the goal production of the grammar.
	 */
	private class GoalProduction extends ProductionImpl {
		// INVARIANT: assumes at most one GoalProduction object per Grammar

		/**
		 * Constructs a goal production with the given right hand side
		 * 
		 * @param rhs
		 */
		public GoalProduction(NonTerminalToken rhs) {
			super(GOAL_SYMBOL, Arrays.asList(new Token[] { rhs }));
		}

		@Override
		public boolean isGoalProduction() {
			return true;
		}

		@Override
		public int productionNumber() {
			return 0;
		}

	}

	/**
	 * Constructs a new, empty grammar.
	 */
	public Grammar() {
		super();
		this.productions = new LinkedList<Production>();
	}

	/**
	 * Ensures that a goal production has been added to the grammar. Makes the
	 * grammar immutable. Triggers the calculation of the set of nullable
	 * non-terminals, the first sets, and the follows sets for this grammar.
	 */
	public void finalizeGrammar() {
		addGoalProduction();
		nullableNonterminals = new NullableNonterminals(this);
		firstSets = new FirstSets(this, nullableNonterminals);
		followSets = new FollowSets(this, nullableNonterminals, firstSets);
	}

	/**
	 * Adds a production to this grammar with the given left-hand and right-hand
	 * sides.
	 * 
	 * @param lhs
	 * @param rhs
	 */
	public void addProduction(NonTerminalToken lhs, List<Token> rhs) {
		if (this.goalProductionAdded) {
			throw new IllegalStateException(
					"cannot add productions after grammar has been finalized");
		}
		add(new ProductionImpl(lhs, rhs));
	}

	private void add(Production production) {
		this.productions.add(production);
		this.nonTerminals.add(production.leftHandSide());
		for (Token t : production.rightHandSide()) {
			if (t.isTerminal()) {
				terminals.add((TerminalToken) t);
			}
			else if (t.isAction()) {
				actions.add((ActionToken) t);
			}
		}
	}

	/**
	 * Adds a goal production if one hasn't been added already. Non-private for
	 * testing.
	 */
	void addGoalProduction() {
		if (this.goalProductionAdded)
			return;
		this.goalProductionAdded = true;
		productions.addFirst(new GoalProduction(productions.getFirst()
				.leftHandSide()));
	}

	/**
	 * @return the goal production of this grammar
	 */
	public Production getGoalProduction() {
		addGoalProduction();
		return productions.getFirst();
	}

	/**
	 * @return an immutable list of the productions in this grammar
	 */
	public List<Production> productions() {
		return Collections.unmodifiableList(this.productions);
	}

	/**
	 * @param c
	 * @return all the productions with the given token as their left-hand side
	 */
	public Set<Production> productionsFor(Token c) {
		Set<Production> result = new TreeSet<Production>();
		for (Production prod : this.productions) {
			if (prod.leftHandSide().equals(c)) {
				result.add(prod);
			}
		}
		return result;
	}

	/**
	 * @return an immutable set of all the non-terminal symbols of this grammar
	 */
	public Set<NonTerminalToken> nonTerminals() {
		return Collections.unmodifiableSet(this.nonTerminals);
	}

	/**
	 * @return an immutable set of all the terminal symbols of this grammar
	 */
	public Set<TerminalToken> terminals() {
		return Collections.unmodifiableSet(this.terminals);
	}

	/**
	 * @return an immutable set of all the action symbols of this grammar
	 */
	public Set<ActionToken> actions() {
		return Collections.unmodifiableSet(this.actions);
	}

	/**
	 * @return the nullable non-terminal symbols of this grammar
	 */
	public NullableNonterminals nullableNonterminals() {
		return this.nullableNonterminals;
	}

	/**
	 * @return all the first sets of this grammar
	 */
	public FirstSets firstSets() {
		return this.firstSets;
	}

	/**
	 * @return all the follow sets of this grammar
	 */
	public FollowSets followSets() {
		return this.followSets;
	}

}
