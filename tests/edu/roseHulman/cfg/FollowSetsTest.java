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
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Test;

import edu.roseHulman.cfg.CFGParser.SyntaxError;

/**
 * Tests follow sets.
 * 
 * @author cclifton
 * 
 */
public class FollowSetsTest {

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testNothingButNothing() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|<<EOF>>",
				"<Start>|<<EOF>>" };
		checkFollow(expectedMappings, Grammars.NothingButNothing);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testSheepNoise() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|<<EOF>>",
				"<S>|<<EOF>>" };
		checkFollow(expectedMappings, Grammars.SheepNoise);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testExpression1_1() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Expr'>|<<EOF>>",
				"<Expr>|<<EOF>>", "<Goal>|<<EOF>>", "<Term>|+", "<Term>|-",
				"<Term>|<<EOF>>" };
		checkFollow(expectedMappings, Grammars.Expression1);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testQuiz7() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Term'>|<<EOF>>",
				"<Term>|<<EOF>>", "<Goal>|<<EOF>>", "<Factor>|*", "<Factor>|/",
				"<Factor>|<<EOF>>" };
		checkFollow(expectedMappings, Grammars.Quiz7);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testQuiz15() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|<<EOF>>",
				"<Stmt>|}", "<Stmt>|<<EOF>>" };
		checkFollow(expectedMappings, Grammars.Quiz15);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testParens() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|<<EOF>>",
				"<Parens>|(", "<Parens>|)", "<Parens>|x", "<Parens>|<<EOF>>" };
		checkFollow(expectedMappings, Grammars.Parens);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testIfThenElse1() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|<<EOF>>",
				"<Stmt>|else", "<Stmt>|<<EOF>>" };
		checkFollow(expectedMappings, Grammars.IfThenElse1);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testIfThenElse1withoutAmbig() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|<<EOF>>",
				"<Stmt>|<<EOF>>", "<WithElse>|else" };
		checkFollow(expectedMappings, Grammars.IfThenElse1withoutAmbig);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testIfThenElse1leftFactored() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|<<EOF>>",
				"<Stmt>|<<EOF>>", "<IfRest>|<<EOF>>", "<WithElse>|else" };
		checkFollow(expectedMappings, Grammars.IfThenElse1leftFactored);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testIfThenElse2() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Assn>|else", "<Assn>|rcb",
				"<Assn>|<<EOF>>", "<Expr>|then", "<Goal>|<<EOF>>",
				"<Stmt>|<<EOF>>", "<Stmt>|rcb", "<WithElse>|else" };
		checkFollow(expectedMappings, Grammars.IfThenElse2);
	}

	private void checkFollow(String[] expectedMappings, String inputGrammar)
			throws IOException, SyntaxError {
		Grammar g = Grammars.getGrammarFrom(inputGrammar);
		g.addGoalProduction();
		NullableNonterminals nullable = new NullableNonterminals(g);
		FirstSets firstS = new FirstSets(g, nullable);
		FollowSets followS = new FollowSets(g, nullable, firstS);
		Map<Token, SortedSet<Token>> followMap = followS.getMap();
		Map<Token, Set<Token>> expected = Grammars
				.buildExpectedMap(expectedMappings);
		assertEquals(expected, followMap);
	}

}
