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

TODO

## Attribution

This code was written by Curtis Clifton and Brian T. Kelley and was obtained
from [CFG Experimenter](http://www.curtclifton.net/cfg-experimenter)

## License

See CFG Exprimenter's provided [license](src/edu/roseHulman/cfg/license.txt)