/*
 * Copyright � 2007�2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

package edu.roseHulman.cfg;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Hand-coded, top-down parser for context free grammars. The grammar of
 * grammars is:
 *
 * <pre>
 * <Grammar> ::= <ProdList>
 *
 * <ProdList> ::= <ProdBlock> <ProdList> 
 *             |
 *
 * <ProdBlock> ::= nt '::=' <RightHandSide>
 *
 * <RightHandSide> ::= <Symbol> <SymbolList> nl <OtherProds> 
 *                  | 'e' nl <OtherProds>
 *
 * <Symbol> ::= nt
 *           |  t
 *
 * <SymbolList> ::= <Symbol> <SymbolList> 
 *               |
 *
 * <OtherProds> ::= '|' <RightHandSide> 
 *               |
 *
 * where nt is a token representing a non-terminal 
 *       nl is a token representing a newline (plus whitespace)
 *       t  is a token representing a terminal symbol
 * </pre>
 *
 * @author cclifton
 */
public class CFGParser {

	/**
	 * Thrown on a syntax error in the CFG.
	 *
	 * @author cclifton
	 */
	public class SyntaxError extends Exception {
		private static final long serialVersionUID = 145527623068151733L;

		/**
		 * Constructs a syntax error exception for the given token.
		 *
		 * @param expected
		 *            the expected token
		 * @param token
		 *            the actual token
		 */
		public SyntaxError(String expected, Token token) {
			super("Expected " + expected + " but got " + token + ".");
		}

		/**
		 * Constructs a syntax error exception for the given token.
		 *
		 * @param expected
		 *            the expected token
		 * @param token
		 *            the actual token
		 */
		public SyntaxError(Token expected, Token token) {
			this(expected.toString(), token);
		}

	}

	private Reader inputGrammar;

	private Scanner scanner;

	private Token nextToken;

	private Grammar grammar;

	/**
	 * Constructs a CFG parser connected to the given input source
	 *
 	 * @param inputGrammer
	 */
	public CFGParser(Reader inputGrammer) {
		this.inputGrammar = inputGrammer;
	}

	/**
	 * Constructs a CFG parser to process the given context-free grammar. This
	 * is a unit test entry point for bypassing the GUI.
	 *
	 * @param inputGrammar a context-free grammar to be parsed
	 */
	public CFGParser(String inputGrammar) {
		this.inputGrammar = new StringReader(inputGrammar);
	}

	/**
	 * @return the context-free grammar read by this parser 
	 */
	public Grammar grammar() {
		return this.grammar;
	}

	/**
	 * Parses the CFG given in the input and sets this.grammar to contain the
	 * resulting grammar.
	 *
	 * @throws IOException
	 * @throws SyntaxError
	 */
	public void parseGrammar() throws IOException, SyntaxError {
		this.grammar = new Grammar();
		this.scanner = new Scanner(inputGrammar);
		scanAnotherToken();
		matchGrammar();
	}

	/**
	 * <pre>
	 * <Grammar> ::= <ProdList>
	 * </pre>
	 *
	 * @throws SyntaxError
	 * @throws IOException
	 */
	private void matchGrammar() throws SyntaxError, IOException {
		if (this.nextToken.isNonTerminal()) {
			matchProductionList();
		} else if (this.nextToken == OperatorToken.NEWLINE) {
			scanAnotherToken();
			matchGrammar();
		} else if (this.nextToken.isEOF()) {
			return;
		} else {
			throw new SyntaxError("non-terminal", this.nextToken);
		}
	}

	/**
	 * <pre>
	 * <ProdList> ::= <ProdBlock> <ProdList> 
	 *             |
	 * </pre>
	 *
	 * @throws SyntaxError
	 * @throws IOException
	 */
	private void matchProductionList() throws SyntaxError, IOException {
		if (this.nextToken.isNonTerminal()) {
			matchProductionBlock();
			matchProductionList();
		} else if (this.nextToken.isEOF()) {
			return;
		} else {
			throw new SyntaxError("non-terminal", this.nextToken);
		}
	}

	/**
	 * <pre>
	 * <ProdBlock> ::= nt '::=' <RightHandSide>
	 * </pre>
	 *
	 * @throws IOException
	 * @throws SyntaxError
	 */
	private void matchProductionBlock() throws IOException, SyntaxError {
		if (this.nextToken.isNonTerminal()) {
			NonTerminalToken lhs = (NonTerminalToken) this.nextToken;
			scanAnotherToken();
			if (this.nextToken != OperatorToken.GOES_TO) {
				throw new SyntaxError(OperatorToken.GOES_TO.toString(),
						this.nextToken);
			}
			scanAnotherToken();
			matchRightHandSide(lhs);
		} else {
			throw new SyntaxError("non-terminal", this.nextToken);
		}
	}

	/**
	 * <pre>
	 * <RightHandSide> ::= <Symbol> <SymbolList> nl <OtherProds> 
	 *                  | 'e' nl <OtherProds>
	 * </pre>
	 *
	 * @param lhs
	 *            the left-hand side of the production
	 * @throws IOException
	 * @throws SyntaxError
	 */
	private void matchRightHandSide(NonTerminalToken lhs) throws IOException,
			SyntaxError {
		List<Token> rhs = new ArrayList<Token>();
		if (this.nextToken.isEmptyString()) {
			rhs.add(this.nextToken);
			scanAnotherToken();
		} else {
			Token firstSym = matchSymbol();
			rhs.add(firstSym);
			matchSymbolList(rhs);
		}
		this.grammar.addProduction(lhs, rhs);
		if (this.nextToken != OperatorToken.NEWLINE) {
			throw new SyntaxError("newline", this.nextToken);
		}
		scanAnotherToken();
		matchOtherProductionsList(lhs);
	}

	/**
	 * <pre>
	 * <Symbol> ::= nt
	 *           |  t
	 * </pre>
	 *
	 * @return the token corresponding to the matched symbol
	 * @throws IOException
	 * @throws SyntaxError
	 */
	private Token matchSymbol() throws IOException, SyntaxError {
		if (nextTokenIsSymbol()) {
			Token result = this.nextToken;
			scanAnotherToken();
			return result;
		}
		throw new SyntaxError("symbol", this.nextToken);
	}

	/**
	 * <pre>
	 * <SymbolList> ::= <Symbol> <SymbolList> 
	 *               |
	 * </pre>
	 *
	 * @param accumulator
	 *            this list of symbols found so far in the current list
	 * @throws IOException
	 * @throws SyntaxError
	 */
	private void matchSymbolList(List<Token> accumulator) throws IOException,
			SyntaxError {
		if (nextTokenIsSymbol()) {
			accumulator.add(this.nextToken);
			scanAnotherToken();
			matchSymbolList(accumulator);
		} else if (this.nextToken == OperatorToken.NEWLINE) {
			return;
		} else {
			throw new SyntaxError("terminal, non-terminal, or newline",
					this.nextToken);
		}
	}

	/**
	 * <pre>
	 * <OtherProds> ::= '|' <RightHandSide> 
	 *               |
	 * </pre>
	 *
	 * @param lhs
	 * @throws SyntaxError
	 * @throws IOException
	 */
	private void matchOtherProductionsList(NonTerminalToken lhs)
			throws SyntaxError, IOException {
		if (this.nextToken == OperatorToken.OR) {
			scanAnotherToken();
			matchRightHandSide(lhs);
		} else if (this.nextToken.isNonTerminal() || this.nextToken.isEOF()) {
			return;
		} else {
			throw new SyntaxError("'|', non-terminal, or EOF", this.nextToken);
		}
	}

	/**
	 * Gets another token from the scanner and stores it in this.nextToken.
	 *
	 * @throws IOException
	 */
	private void scanAnotherToken() throws IOException {
		this.nextToken = this.scanner.nextToken();
	}

	/**
	 * @return true if the next token in the input is a symbol -- non-terminal,
	 *         or terminal
	 */
	private boolean nextTokenIsSymbol() {
		return this.nextToken.isNonTerminal() || this.nextToken.isTerminal();
	}

}
