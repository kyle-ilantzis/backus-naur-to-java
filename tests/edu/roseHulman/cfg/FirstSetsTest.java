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
 * Tests first sets.
 * 
 * @author cclifton
 * 
 */
public class FirstSetsTest {

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testNothingButNothing() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|e", "<Start>|e",
				"e|e", "<<EOF>>|<<EOF>>" };
		checkFirst(expectedMappings, Grammars.NothingButNothing);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testSheepNoise() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|baa", "<Goal>|e",
				"<S>|baa", "<S>|e", "baa|baa", "e|e", "<<EOF>>|<<EOF>>" };
		checkFirst(expectedMappings, Grammars.SheepNoise);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testExpression1_1() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Expr'>|+", "<Expr'>|-",
				"<Expr'>|e", "<Expr>|num", "<Goal>|num", "<Term>|num", "+|+",
				"-|-", "num|num", "e|e", "<<EOF>>|<<EOF>>" };
		checkFirst(expectedMappings, Grammars.Expression1);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testQuiz7() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Term'>|*", "<Term'>|/",
				"<Term'>|e", "<Term>|num", "<Goal>|num", "<Factor>|num", "*|*",
				"/|/", "num|num", "e|e", "<<EOF>>|<<EOF>>" };
		checkFirst(expectedMappings, Grammars.Quiz7);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testQuiz15() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|null;", "<Goal>|{",
				"<Stmt>|null;", "<Stmt>|{", "null;|null;", "{|{", "}|}", "e|e",
				"<<EOF>>|<<EOF>>" };
		checkFirst(expectedMappings, Grammars.Quiz15);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testParens() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|(", "<Goal>|x",
				"<Parens>|(", "<Parens>|x", "(|(", ")|)", "x|x", "e|e",
				"<<EOF>>|<<EOF>>" };
		checkFirst(expectedMappings, Grammars.Parens);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testIfThenElse1() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|assign",
				"<Goal>|if", "<Stmt>|assign", "<Stmt>|if", "assign|assign",
				"expr|expr", "if|if", "then|then", "else|else", "e|e",
				"<<EOF>>|<<EOF>>" };
		checkFirst(expectedMappings, Grammars.IfThenElse1);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testIfThenElse1withoutAmbig() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|assn", "<Goal>|if",
				"<Stmt>|assn", "<Stmt>|if", "<WithElse>|assn", "<WithElse>|if",
				"assn|assn", "expr|expr", "if|if", "then|then", "else|else",
				"e|e", "<<EOF>>|<<EOF>>" };
		checkFirst(expectedMappings, Grammars.IfThenElse1withoutAmbig);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testIfThenElse1leftFactored() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Goal>|assn", "<Goal>|if",
				"<IfRest>|assn", "<IfRest>|if", "<Stmt>|assn", "<Stmt>|if",
				"<WithElse>|assn", "<WithElse>|if", "assn|assn", "expr|expr",
				"if|if", "then|then", "else|else", "e|e", "<<EOF>>|<<EOF>>" };
		checkFirst(expectedMappings, Grammars.IfThenElse1leftFactored);
	}

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws SyntaxError
	 */
	@Test
	public void testIfThenElse2() throws IOException, SyntaxError {
		String[] expectedMappings = new String[] { "<Assn>|lcb", "<Assn>|assn",
				"<Expr>|false", "<Expr>|true", "<Goal>|assn", "<Goal>|if",
				"<Goal>|lcb", "<Stmt>|assn", "<Stmt>|if", "<Stmt>|lcb",
				"<WithElse>|assn", "<WithElse>|if", "<WithElse>|lcb",
				"assn|assn", "else|else", "false|false", "if|if", "lcb|lcb",
				"rcb|rcb", "then|then", "true|true", "e|e", "<<EOF>>|<<EOF>>" };
		checkFirst(expectedMappings, Grammars.IfThenElse2);
	}

	private void checkFirst(String[] expectedMappings, String inputGrammar)
			throws IOException, SyntaxError {
		Grammar g = Grammars.getGrammarFrom(inputGrammar);
		g.addGoalProduction();
		NullableNonterminals nullable = new NullableNonterminals(g);
		FirstSets fs = new FirstSets(g, nullable);
		Map<Token, SortedSet<Token>> firstMap = fs.getMap();
		Map<Token, Set<Token>> expected = Grammars
				.buildExpectedMap(expectedMappings);
		assertEquals(expected, firstMap);
	}

}
