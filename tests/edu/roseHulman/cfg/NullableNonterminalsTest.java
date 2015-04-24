/*
 * Copyright © 2007Ð2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import edu.roseHulman.cfg.CFGParser.SyntaxError;

/**
 * Tests cases for NullableNonterminals.
 * 
 * @author cclifton
 * 
 */
public class NullableNonterminalsTest {

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testNothingButNothing() throws IOException, SyntaxError {
		String[] expectedNonterminals = new String[] { "<Goal>", "<Start>" };
		checkNullable(expectedNonterminals, Grammars.NothingButNothing);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testSheepNoise() throws IOException, SyntaxError {
		String[] expectedNonterminals = new String[] { "<Goal>", "<S>" };
		checkNullable(expectedNonterminals, Grammars.SheepNoise);
	}

/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testExpression1_1() throws IOException, SyntaxError {
		String[] expectedNonterminals = new String[] { "<Expr'>" };
		checkNullable(expectedNonterminals, Grammars.Expression1);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testExpression1_2() throws IOException, SyntaxError {
		String[] expectedNonterminals = new String[] { "<Goal>", "<Expr'>", "<Expr>",
				"<Term>" };
		// additional production makes <Term> nullable, and hence <Expr> also.
		checkNullable(expectedNonterminals, Grammars.Expression1 + "| e\n");
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testQuiz7() throws IOException, SyntaxError {
		String[] expectedNonterminals = new String[] { "<Term'>" };
		checkNullable(expectedNonterminals, Grammars.Quiz7);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testQuiz15() throws IOException, SyntaxError {
		String[] expectedNonterminals = new String[] {};
		checkNullable(expectedNonterminals, Grammars.Quiz15);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testParens() throws IOException, SyntaxError {
		String[] expectedNonterminals = new String[] {};
		checkNullable(expectedNonterminals, Grammars.Parens);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testIfThenElse1() throws IOException, SyntaxError {
		String[] expectedNonterminals = new String[] {};
		checkNullable(expectedNonterminals, Grammars.IfThenElse1);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testIfThenElse1withoutAmbig() throws IOException, SyntaxError {
		String[] expectedNonterminals = new String[] {};
		checkNullable(expectedNonterminals, Grammars.IfThenElse1withoutAmbig);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testIfThenElse1leftFactored() throws IOException, SyntaxError {
		String[] expectedNonterminals = new String[] {};
		checkNullable(expectedNonterminals, Grammars.IfThenElse1leftFactored);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testIfThenElse2() throws IOException, SyntaxError {
		String[] expectedNonterminals = new String[] {};
		checkNullable(expectedNonterminals, Grammars.IfThenElse2);
	}

	private void checkNullable(String[] expectedNonterminals,
			String inputGrammar) throws IOException, SyntaxError {
		Grammar g = Grammars.getGrammarFrom(inputGrammar);
		g.addGoalProduction();
		NullableNonterminals nullable = new NullableNonterminals(g);
		Set<Token> nullableTokens = nullable.getSet();
		Set<Token> expected = buildExpectedSet(expectedNonterminals);
		assertEquals(expected, nullableTokens);
	}

	private Set<Token> buildExpectedSet(String[] strings) {
		HashSet<Token> result = new HashSet<Token>();
		for (String s : strings) {
			result.add(Grammars.tokenFromString(s));
		}
		return result;
	}
}
