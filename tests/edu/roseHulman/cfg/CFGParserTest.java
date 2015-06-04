package edu.roseHulman.cfg;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CFGParserTest {

    @Test
    public void testSingleProduction() throws Exception {

        String input = "<S> ::= a\n";

        Grammar g = Grammars.getGrammarFrom( input );

        assertEquals(
                Arrays.asList( Grammars.tokensFromStrings( "<Goal>", "<S>" ), Grammars.tokensFromStrings( "<S>", "a" ) ),
                Grammars.flattenProductions( g )
        );
    }

    @Test
    public void testSingleProductionWithActions() throws Exception {

        String input = "<S> ::= {1} a {2}\n";

        Grammar g = Grammars.getGrammarFrom(input);

        assertEquals(
                Arrays.asList( Grammars.tokensFromStrings( "<Goal>", "<S>" ), Grammars.tokensFromStrings( "<S>", "{1}", "a", "{2}" ) ),
                Grammars.flattenProductions( g )
        );
    }

    @Test
    public void testWithEpsilonProductionAndActions() throws Exception {

        String input = "<S> ::= a <B> c\n";
        input += "<B> ::= {a0} e {a1} {a2} {a3}\n";

        Grammar g = Grammars.getGrammarFrom( input );

        assertEquals(
                Arrays.asList( Grammars.tokensFromStrings( "<Goal>", "<S>" ), Grammars.tokensFromStrings( "<S>", "a", "<B>", "c" ), Grammars.tokensFromStrings( "<B>", "{a0}", "e", "{a1}", "{a2}", "{a3}" ) ),
                Grammars.flattenProductions( g )
        );
    }

    @Test
    public void testCommentAtStart() throws Exception {

        String input = "/*\n" +
                       " * This is a comment block at start\n" +
                       " */" +
                       "<S> ::= a\n";

        Grammar g = Grammars.getGrammarFrom( input );

        assertEquals(
                Arrays.asList( Grammars.tokensFromStrings( "<Goal>", "<S>" ), Grammars.tokensFromStrings( "<S>", "a" ) ),
                Grammars.flattenProductions( g )
        );
    }

    @Test
    public void testCommentAtEnd() throws Exception {

        String input = "<S> ::= a\n" +
                       "/*\n" +
                       " * This is a comment block at end\n" +
                       " */";

        Grammar g = Grammars.getGrammarFrom( input );

        assertEquals(
                Arrays.asList( Grammars.tokensFromStrings( "<Goal>", "<S>" ), Grammars.tokensFromStrings( "<S>", "a" ) ),
                Grammars.flattenProductions( g )
        );
    }

    @Test
    public void testCommentBetweenProductions() throws Exception {

        String input = "<S> ::= a <A>\n" +
                        "/*\n" +
                        " * This is a comment block between\n" +
                        " */\n" +
                       "<A> ::= b\n";

        Grammar g = Grammars.getGrammarFrom( input );

        assertEquals(
                Arrays.asList( Grammars.tokensFromStrings( "<Goal>", "<S>" ), Grammars.tokensFromStrings( "<S>", "a", "<A>" ), Grammars.tokensFromStrings( "<A>", "b" ) ),
                Grammars.flattenProductions( g )
        );
    }

    @Test
    public void testCommentBetweenTerminals() throws Exception {

        String input = "<S> ::= /* c1 */ a /* c2 */ <A>\n";

        Grammar g = Grammars.getGrammarFrom( input );

        assertEquals(
                Arrays.asList( Grammars.tokensFromStrings( "<Goal>", "<S>" ), Grammars.tokensFromStrings( "<S>", "a", "<A>" ) ),
                Grammars.flattenProductions( g )
        );
    }

    @Test(expected = CFGParser.SyntaxError.class)
         public void testWithToManyEpsilons() throws Exception {

        String input = "<S> ::= e e\n";
        Grammars.getGrammarFrom( input );
    }

    @Test(expected = CFGParser.SyntaxError.class)
    public void testWithSymbolsAfterEpsilon() throws Exception {

        String input = "<S> ::= e a\n";
        Grammars.getGrammarFrom( input );
    }

    @Test(expected = CFGParser.SyntaxError.class)
    public void testWithSymbolsBeforeEpsilon() throws Exception {

        String input = "<S> ::= a e b\n";
        Grammars.getGrammarFrom( input );
    }

    @Test(expected = CFGParser.SyntaxError.class)
    public void testWithOnlyActions() throws Exception {

        String input = "<S> ::= {a0} {a1}\n";
        Grammars.getGrammarFrom( input );
    }

    @Test(expected = CFGParser.SyntaxError.class)
    public void testLiterallyEmptyProduction() throws Exception {

        String input = "<S> ::=\n";
        Grammars.getGrammarFrom( input );
    }

    @Test(expected = CFGParser.SyntaxError.class)
    public void testGoesToSymbolOmitted() throws Exception {

        String input = "<S> a b c\n";
        Grammars.getGrammarFrom( input );
    }

    @Test(expected = CFGParser.SyntaxError.class)
    public void testOROperatorOnSameLine() throws Exception {

        String input = "<S> ::= a | b\n";
        Grammars.getGrammarFrom( input );
    }

    @Test(expected = CFGParser.SyntaxError.class)
    public void testNewlineOmitted() throws Exception {

        String input = "<S> ::= a | b";
        Grammars.getGrammarFrom( input );
    }

    @Test(expected = CFGParser.SyntaxError.class)
    public void testStartProductionWithTerminal() throws Exception {

        String input = "a ::= <S>\n";
        Grammars.getGrammarFrom( input );
    }
}
