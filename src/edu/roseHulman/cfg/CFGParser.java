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
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

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
	 * What an action symbol is surrounded by on its left and right
	 */
	private static final String ACTION_LEFT = "{";
	private static final String ACTION_RIGHT = "}";

	/**
	 * Thrown on a syntax errors in the CFG.
	 */
	public class SyntaxError extends Exception {

		private final List<String> errors;

		private SyntaxError( List<String> errors ) {
			super( "There are " + errors.size() + " syntax errors in the grammar." );
			this.errors = errors;
		}

		public List<String> getErrors() {
			return Collections.unmodifiableList( this.errors );
		}
	}

	// Used when skipping over tokens on a syntax error. When no more tokens are in the input
	// this is a control mechanism to stop execution.
	private class EOFTokenException extends RuntimeException {}

	private Reader inputGrammar;

	private Scanner scanner;

	private Token nextToken;

	private Grammar grammar;

	private List<String> syntaxErrors;

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
	 * @throws edu.roseHulman.cfg.CFGParser.SyntaxError
	 */
	public void parseGrammar() throws IOException, SyntaxError {
		this.grammar = new Grammar();
		this.scanner = new Scanner(inputGrammar);
		this.syntaxErrors = new ArrayList<>();

		scanAnotherToken();

		try { matchGrammar(); }
		catch( EOFTokenException e ) {}

		if ( !syntaxErrors.isEmpty() ) {
			throw new SyntaxError( syntaxErrors );
		}

		this.grammar().finalizeGrammar();
	}

	/**
	 * Make note of a syntax error for the current toke.
	 * Use a predicate to skip over next scanned tokens until predicate returns true for a scanned token.
	 *
	 * @param expected
	 *            a message about the expected token to display to users
	 * @param p
	 * 			predicate used to skip over next scanned tokens
	 *
	 * @throws java.io.IOException
	 * @throws edu.roseHulman.cfg.CFGParser.EOFTokenException If EOF is encountered and p does not return true.
	 */
	private void syntaxError( String expected, Predicate<Token> p ) throws EOFTokenException, IOException {

		syntaxErrors.add("Expected " + expected + " but got " + this.nextToken + " on line " + (this.scanner.line() + 1) + " near column " + (this.scanner.column() + 1 ) );

		while ( !p.test( this.nextToken ) ) {

			scanAnotherToken();

			if (this.nextToken.isEOF() && !p.test( this.nextToken ) ) {
				throw new EOFTokenException();
			}
		}
	}

	private void matchOperator( OperatorToken op ) throws IOException {
		if (this.nextToken != op) {
			syntaxError( op.getPrettyText(), op::equals );
		}
	}

	/**
	 * <pre>
	 * <Grammar> ::= <ProdList>
	 * </pre>
	 *
	 * @throws IOException
	 */
	private void matchGrammar() throws IOException {
		if (this.nextToken.isNonTerminal()) {
			matchProductionList();
		} else if (this.nextToken == OperatorToken.NEWLINE) {
			scanAnotherToken();
			matchGrammar();
		} else if (this.nextToken.isEOF()) {
			return;
		} else {
			syntaxError( "non-terminal", t -> t.isNonTerminal() || t.isEOF() );
			matchGrammar();
		}
	}

	/**
	 * <pre>
	 * <ProdList> ::= <ProdBlock> <ProdList> 
	 *             |
	 * </pre>
	 *
	 * @throws IOException
	 */
	private void matchProductionList() throws IOException {
		if (this.nextToken.isNonTerminal()) {
			matchProductionBlock();
			matchProductionList();
		} else if (this.nextToken.isEOF()) {
			return;
		} else {
			syntaxError( "non-terminal", t -> t.isNonTerminal() || t.isEOF() );
			matchProductionList();
		}
	}

	/**
	 * <pre>
	 * <ProdBlock> ::= nt '::=' <RightHandSide>
	 * </pre>
	 *
	 * @throws IOException
	 */
	private void matchProductionBlock() throws IOException {
		if (this.nextToken.isNonTerminal()) {
			NonTerminalToken lhs = (NonTerminalToken) this.nextToken;
			scanAnotherToken();
			matchOperator(OperatorToken.GOES_TO);
			scanAnotherToken();
			matchRightHandSide(lhs);
		} else {
			syntaxError( "non-terminal", Token::isNonTerminal );
			matchProductionBlock();
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
	 */
	private void matchRightHandSide(NonTerminalToken lhs) throws IOException {
		List<Token> rhs = new ArrayList<Token>();

		int line = this.scanner.line();

		if (this.nextToken.isEmptyString()) {
			rhs.add(this.nextToken);
			scanAnotherToken();
			matchActionList(rhs);
		} else {
			Token firstSym = matchSymbol();
			rhs.add(firstSym);
			if (firstSym.isAction()) {
				matchSymbolOrEmptyList(rhs);
			}
			else {
				matchSymbolList(rhs);
			}
		}
		this.grammar.addProduction(lhs, rhs, line + 1);
		if (this.nextToken.isEOF()) { return; }
		matchOperator(OperatorToken.NEWLINE);
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
	 */
	private Token matchSymbol() throws IOException {
		if (nextTokenIsSymbol()) {
			Token result = this.nextToken;
			scanAnotherToken();
			return result;
		}
		else {
			syntaxError( "action, terminal, non-terminal", t -> t.isAction() || t.isTerminal() || t.isNonTerminal() );
			return matchSymbol();
		}
	}

	private void matchSymbolOrEmptyList(List<Token> accumulator) throws IOException {

		if (nextTokenIsSymbol() || this.nextToken.isEmptyString()) {
			Token tk = this.nextToken;
			accumulator.add(tk);
			scanAnotherToken();

			if ( tk.isEmptyString() ) {
				matchActionList(accumulator);
			}
			else if ( tk.isTerminal() || tk.isNonTerminal() ) {
				matchSymbolList(accumulator);
			}
			else {
				matchSymbolOrEmptyList(accumulator);
			}
		} else {

			syntaxError( "action, terminal or non-terminal", t -> t.isAction() || t.isTerminal() || t.isNonTerminal() );
			matchSymbolOrEmptyList(accumulator);
		}
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
	 */
	private void matchSymbolList(List<Token> accumulator) throws IOException {
		if (nextTokenIsSymbol()) {
			accumulator.add(this.nextToken);
			scanAnotherToken();
			matchSymbolList(accumulator);
		} else if (this.nextToken == OperatorToken.NEWLINE || this.nextToken.isEOF()) {
			return;
		} else {
			syntaxError( "action, terminal, non-terminal, or newline", t -> t.isAction() || t.isTerminal() || t.isNonTerminal() );
			matchSymbolList(accumulator);
		}
	}

	private void matchActionList(List<Token> accumulator) throws IOException {

		if (this.nextToken.isAction()) {
			accumulator.add(this.nextToken);
			scanAnotherToken();
			matchActionList(accumulator);
		} else if (this.nextToken == OperatorToken.NEWLINE || this.nextToken.isEOF()) {
			return;
		} else {
			syntaxError( "action or newline", t -> t.isAction() || t == OperatorToken.NEWLINE );
			matchActionList(accumulator);
		}
	}

	/**
	 * <pre>
	 * <OtherProds> ::= '|' <RightHandSide> 
	 *               |
	 * </pre>
	 *
	 * @param lhs
	 * @throws IOException
	 */
	private void matchOtherProductionsList(NonTerminalToken lhs) throws IOException {
		if (this.nextToken == OperatorToken.OR) {
			scanAnotherToken();
			matchRightHandSide(lhs);
		} else if (this.nextToken.isNonTerminal() || this.nextToken.isEOF()) {
			return;
		} else {
			syntaxError("'|', non-terminal, or EOF", t -> t == OperatorToken.OR || t.isNonTerminal() || t.isEOF() );
			matchOtherProductionsList(lhs);
		}
	}

	/**
	 * Gets another token from the scanner and stores it in this.nextToken.
	 *
	 * @throws IOException
	 */
	private void scanAnotherToken() throws IOException {

		Token tk = this.scanner.nextToken();
		String txt = tk.toString();

		if ( txt.startsWith( ACTION_LEFT ) && txt.endsWith( ACTION_RIGHT) ) {
			this.nextToken = new ActionToken( txt );
		}
		else {
			this.nextToken = tk;
		}
	}

	/**
	 * @return true if the next token in the input is a symbol -- non-terminal,
	 *         or terminal
	 */
	private boolean nextTokenIsSymbol() {
		return this.nextToken.isNonTerminal() || this.nextToken.isTerminal() || this.nextToken.isAction();
	}

}
