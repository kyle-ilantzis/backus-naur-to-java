package backusnaurtojava;

import edu.roseHulman.cfg.CFGParser;
import edu.roseHulman.cfg.Grammar;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException, CFGParser.SyntaxError {

        InputStream in = Main.class.getResourceAsStream("expr.cfg");

        CFGParser cfgParser = new CFGParser(new InputStreamReader(in));
        cfgParser.parseGrammar();
        Grammar grammar = cfgParser.grammar();

        System.out.println(grammar);
    }
}
