# Backus-Naur to Java

Transform a context free grammar described in [BNF](http://en.wikipedia.org/wiki/Backus–Naur_Form) to
an in memory representation suitable to use in a Java project.

## Usage

```java

Reader in = //...

// OR

String in = //...

// Then

CFGParser cfgParser = new CFGParser( in );
cfgParser.parseGrammar();
Grammar grammar = cfgParser.grammar();

```

## Notation

A production is defined as follows

    <A> ::= a <B>

Where **&lt;A&gt;** identifies the non-terminal **A**, **a** is a terminal and **&lt;B&gt;** refers to the non-terminal
**B**.

You can combined many right hands sides with the pipe symbol **|** making sure each right hand side starts on a **newline**.

    <A> ::= a <B>
        | b

An empty production can be define with the reserved symbol **e**

    <A> ::= e

Semantic actions can be embedded in the grammar as follows

    <A> ::= a {the_action} b

Where **{the_action}** identifies the action **the_action**.

You can write C-style comments with the reserved symbols **/\*** and **\*/**

    /* The sheep on the farm goes ... baa baa baa! */
    <S> ::= baa <S>
        | e

## Attribution

This code was written by Curtis Clifton and Brian T. Kelley and was obtained
from [CFG Experimenter](http://www.curtclifton.net/cfg-experimenter)

## License

See CFG Exeprimenter's provided [license](src/edu/roseHulman/cfg/license.txt)

Any changes made that do not fall under CFG Experimenter's license consideration are part
of the public domain.