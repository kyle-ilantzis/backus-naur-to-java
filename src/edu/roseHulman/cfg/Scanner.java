/* The following code was generated by JFlex 1.6.1 */

/*
 * Copyright � 2007�2010, Curtis Clifton and Brian T. Kelley
 * 
 * All rights reserved.
 * 
 * See license.txt for details.
 */

/***************************************************
 * Lexical grammar for CFGs 
 * by Curt Clifton
 * Rose-Hulman Institute of Technology
 * Copyright (C) 2006-07
 ***************************************************/
package edu.roseHulman.cfg;

// --------------------------------------------------
// User Code
// --------------------------------------------------


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>cfg.lex</tt>
 */
class Scanner {

  /** This character denotes the end of file */
  private static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  private static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\10\11\1\1\1\2\1\4\1\4\1\3\1\4\16\11\4\0\1\2"+
    "\3\0\1\10\2\0\1\11\2\0\1\7\4\0\1\6\12\11\1\13"+
    "\1\0\1\16\1\14\1\17\2\0\32\10\4\0\1\10\1\0\4\10"+
    "\1\12\25\10\1\0\1\15\2\0\6\11\1\5\32\11\2\0\4\10"+
    "\4\0\1\10\2\0\1\11\7\0\1\10\4\0\1\10\5\0\27\10"+
    "\1\0\37\10\1\0\u01ca\10\4\0\14\10\16\0\5\10\7\0\1\10"+
    "\1\0\1\10\21\0\160\11\5\10\1\0\2\10\2\0\4\10\10\0"+
    "\1\10\1\0\3\10\1\0\1\10\1\0\24\10\1\0\123\10\1\0"+
    "\213\10\1\0\5\11\2\0\236\10\11\0\46\10\2\0\1\10\7\0"+
    "\47\10\7\0\1\10\1\0\55\11\1\0\1\11\1\0\2\11\1\0"+
    "\2\11\1\0\1\11\10\0\33\10\5\0\3\10\15\0\5\11\6\0"+
    "\1\10\4\0\13\11\5\0\53\10\37\11\4\0\2\10\1\11\143\10"+
    "\1\0\1\10\10\11\1\0\6\11\2\10\2\11\1\0\4\11\2\10"+
    "\12\11\3\10\2\0\1\10\17\0\1\11\1\10\1\11\36\10\33\11"+
    "\2\0\131\10\13\11\1\10\16\0\12\11\41\10\11\11\2\10\4\0"+
    "\1\10\5\0\26\10\4\11\1\10\11\11\1\10\3\11\1\10\5\11"+
    "\22\0\31\10\3\11\104\0\1\10\1\0\13\10\67\0\33\11\1\0"+
    "\4\11\66\10\3\11\1\10\22\11\1\10\7\11\12\10\2\11\2\0"+
    "\12\11\1\0\7\10\1\0\7\10\1\0\3\11\1\0\10\10\2\0"+
    "\2\10\2\0\26\10\1\0\7\10\1\0\1\10\3\0\4\10\2\0"+
    "\1\11\1\10\7\11\2\0\2\11\2\0\3\11\1\10\10\0\1\11"+
    "\4\0\2\10\1\0\3\10\2\11\2\0\12\11\4\10\7\0\1\10"+
    "\5\0\3\11\1\0\6\10\4\0\2\10\2\0\26\10\1\0\7\10"+
    "\1\0\2\10\1\0\2\10\1\0\2\10\2\0\1\11\1\0\5\11"+
    "\4\0\2\11\2\0\3\11\3\0\1\11\7\0\4\10\1\0\1\10"+
    "\7\0\14\11\3\10\1\11\13\0\3\11\1\0\11\10\1\0\3\10"+
    "\1\0\26\10\1\0\7\10\1\0\2\10\1\0\5\10\2\0\1\11"+
    "\1\10\10\11\1\0\3\11\1\0\3\11\2\0\1\10\17\0\2\10"+
    "\2\11\2\0\12\11\1\0\1\10\17\0\3\11\1\0\10\10\2\0"+
    "\2\10\2\0\26\10\1\0\7\10\1\0\2\10\1\0\5\10\2\0"+
    "\1\11\1\10\7\11\2\0\2\11\2\0\3\11\10\0\2\11\4\0"+
    "\2\10\1\0\3\10\2\11\2\0\12\11\1\0\1\10\20\0\1\11"+
    "\1\10\1\0\6\10\3\0\3\10\1\0\4\10\3\0\2\10\1\0"+
    "\1\10\1\0\2\10\3\0\2\10\3\0\3\10\3\0\14\10\4\0"+
    "\5\11\3\0\3\11\1\0\4\11\2\0\1\10\6\0\1\11\16\0"+
    "\12\11\11\0\1\10\7\0\3\11\1\0\10\10\1\0\3\10\1\0"+
    "\27\10\1\0\12\10\1\0\5\10\3\0\1\10\7\11\1\0\3\11"+
    "\1\0\4\11\7\0\2\11\1\0\2\10\6\0\2\10\2\11\2\0"+
    "\12\11\22\0\2\11\1\0\10\10\1\0\3\10\1\0\27\10\1\0"+
    "\12\10\1\0\5\10\2\0\1\11\1\10\7\11\1\0\3\11\1\0"+
    "\4\11\7\0\2\11\7\0\1\10\1\0\2\10\2\11\2\0\12\11"+
    "\1\0\2\10\17\0\2\11\1\0\10\10\1\0\3\10\1\0\51\10"+
    "\2\0\1\10\7\11\1\0\3\11\1\0\4\11\1\10\10\0\1\11"+
    "\10\0\2\10\2\11\2\0\12\11\12\0\6\10\2\0\2\11\1\0"+
    "\22\10\3\0\30\10\1\0\11\10\1\0\1\10\2\0\7\10\3\0"+
    "\1\11\4\0\6\11\1\0\1\11\1\0\10\11\22\0\2\11\15\0"+
    "\60\10\1\11\2\10\7\11\4\0\10\10\10\11\1\0\12\11\47\0"+
    "\2\10\1\0\1\10\2\0\2\10\1\0\1\10\2\0\1\10\6\0"+
    "\4\10\1\0\7\10\1\0\3\10\1\0\1\10\1\0\1\10\2\0"+
    "\2\10\1\0\4\10\1\11\2\10\6\11\1\0\2\11\1\10\2\0"+
    "\5\10\1\0\1\10\1\0\6\11\2\0\12\11\2\0\4\10\40\0"+
    "\1\10\27\0\2\11\6\0\12\11\13\0\1\11\1\0\1\11\1\0"+
    "\1\11\4\0\2\11\10\10\1\0\44\10\4\0\24\11\1\0\2\11"+
    "\5\10\13\11\1\0\44\11\11\0\1\11\71\0\53\10\24\11\1\10"+
    "\12\11\6\0\6\10\4\11\4\10\3\11\1\10\3\11\2\10\7\11"+
    "\3\10\4\11\15\10\14\11\1\10\17\11\2\0\46\10\1\0\1\10"+
    "\5\0\1\10\2\0\53\10\1\0\u014d\10\1\0\4\10\2\0\7\10"+
    "\1\0\1\10\1\0\4\10\2\0\51\10\1\0\4\10\2\0\41\10"+
    "\1\0\4\10\2\0\7\10\1\0\1\10\1\0\4\10\2\0\17\10"+
    "\1\0\71\10\1\0\4\10\2\0\103\10\2\0\3\11\40\0\20\10"+
    "\20\0\125\10\14\0\u026c\10\2\0\21\10\1\0\32\10\5\0\113\10"+
    "\3\0\3\10\17\0\15\10\1\0\4\10\3\11\13\0\22\10\3\11"+
    "\13\0\22\10\2\11\14\0\15\10\1\0\3\10\1\0\2\11\14\0"+
    "\64\10\40\11\3\0\1\10\3\0\2\10\1\11\2\0\12\11\41\0"+
    "\3\11\2\0\12\11\6\0\130\10\10\0\51\10\1\11\1\10\5\0"+
    "\106\10\12\0\35\10\3\0\14\11\4\0\14\11\12\0\12\11\36\10"+
    "\2\0\5\10\13\0\54\10\4\0\21\11\7\10\2\11\6\0\12\11"+
    "\46\0\27\10\5\11\4\0\65\10\12\11\1\0\35\11\2\0\13\11"+
    "\6\0\12\11\15\0\1\10\130\0\5\11\57\10\21\11\7\10\4\0"+
    "\12\11\21\0\11\11\14\0\3\11\36\10\15\11\2\10\12\11\54\10"+
    "\16\11\14\0\44\10\24\11\10\0\12\11\3\0\3\10\12\11\44\10"+
    "\122\0\3\11\1\0\25\11\4\10\1\11\4\10\3\11\2\10\11\0"+
    "\300\10\47\11\25\0\4\11\u0116\10\2\0\6\10\2\0\46\10\2\0"+
    "\6\10\2\0\10\10\1\0\1\10\1\0\1\10\1\0\1\10\1\0"+
    "\37\10\2\0\65\10\1\0\7\10\1\0\1\10\3\0\3\10\1\0"+
    "\7\10\3\0\4\10\2\0\6\10\4\0\15\10\5\0\3\10\1\0"+
    "\7\10\16\0\5\11\30\0\1\4\1\4\5\11\20\0\2\10\23\0"+
    "\1\10\13\0\5\11\5\0\6\11\1\0\1\10\15\0\1\10\20\0"+
    "\15\10\3\0\33\10\25\0\15\11\4\0\1\11\3\0\14\11\21\0"+
    "\1\10\4\0\1\10\2\0\12\10\1\0\1\10\3\0\5\10\6\0"+
    "\1\10\1\0\1\10\1\0\1\10\1\0\4\10\1\0\13\10\2\0"+
    "\4\10\5\0\5\10\4\0\1\10\21\0\51\10\u0a77\0\57\10\1\0"+
    "\57\10\1\0\205\10\6\0\4\10\3\11\2\10\14\0\46\10\1\0"+
    "\1\10\5\0\1\10\2\0\70\10\7\0\1\10\17\0\1\11\27\10"+
    "\11\0\7\10\1\0\7\10\1\0\7\10\1\0\7\10\1\0\7\10"+
    "\1\0\7\10\1\0\7\10\1\0\7\10\1\0\40\11\57\0\1\10"+
    "\u01d5\0\3\10\31\0\11\10\6\11\1\0\5\10\2\0\5\10\4\0"+
    "\126\10\2\0\2\11\2\0\3\10\1\0\132\10\1\0\4\10\5\0"+
    "\51\10\3\0\136\10\21\0\33\10\65\0\20\10\u0200\0\u19b6\10\112\0"+
    "\u51cd\10\63\0\u048d\10\103\0\56\10\2\0\u010d\10\3\0\20\10\12\11"+
    "\2\10\24\0\57\10\1\11\4\0\12\11\1\0\31\10\7\0\1\11"+
    "\120\10\2\11\45\0\11\10\2\0\147\10\2\0\4\10\1\0\4\10"+
    "\14\0\13\10\115\0\12\10\1\11\3\10\1\11\4\10\1\11\27\10"+
    "\5\11\20\0\1\10\7\0\64\10\14\0\2\11\62\10\21\11\13\0"+
    "\12\11\6\0\22\11\6\10\3\0\1\10\4\0\12\11\34\10\10\11"+
    "\2\0\27\10\15\11\14\0\35\10\3\0\4\11\57\10\16\11\16\0"+
    "\1\10\12\11\46\0\51\10\16\11\11\0\3\10\1\11\10\10\2\11"+
    "\2\0\12\11\6\0\27\10\3\0\1\10\1\11\4\0\60\10\1\11"+
    "\1\10\3\11\2\10\2\11\5\10\2\11\1\10\1\11\1\10\30\0"+
    "\3\10\2\0\13\10\5\11\2\0\3\10\2\11\12\0\6\10\2\0"+
    "\6\10\2\0\6\10\11\0\7\10\1\0\7\10\221\0\43\10\10\11"+
    "\1\0\2\11\2\0\12\11\6\0\u2ba4\10\14\0\27\10\4\0\61\10"+
    "\u2104\0\u016e\10\2\0\152\10\46\0\7\10\14\0\5\10\5\0\1\10"+
    "\1\11\12\10\1\0\15\10\1\0\5\10\1\0\1\10\1\0\2\10"+
    "\1\0\2\10\1\0\154\10\41\0\u016b\10\22\0\100\10\2\0\66\10"+
    "\50\0\15\10\3\0\20\11\20\0\7\11\14\0\2\10\30\0\3\10"+
    "\31\0\1\10\6\0\5\10\1\0\207\10\2\0\1\11\4\0\1\10"+
    "\13\0\12\11\7\0\32\10\4\0\1\10\1\0\32\10\13\0\131\10"+
    "\3\0\6\10\2\0\6\10\2\0\6\10\2\0\3\10\3\0\2\10"+
    "\3\0\2\10\22\0\3\11\4\0\14\10\1\0\32\10\1\0\23\10"+
    "\1\0\2\10\1\0\17\10\2\0\16\10\42\0\173\10\105\0\65\10"+
    "\210\0\1\11\202\0\35\10\3\0\61\10\57\0\37\10\21\0\33\10"+
    "\65\0\36\10\2\0\44\10\4\0\10\10\1\0\5\10\52\0\236\10"+
    "\2\0\12\11\u0356\0\6\10\2\0\1\10\1\0\54\10\1\0\2\10"+
    "\3\0\1\10\2\0\27\10\252\0\26\10\12\0\32\10\106\0\70\10"+
    "\6\0\2\10\100\0\1\10\3\11\1\0\2\11\5\0\4\11\4\10"+
    "\1\0\3\10\1\0\33\10\4\0\3\11\4\0\1\11\40\0\35\10"+
    "\203\0\66\10\12\0\26\10\12\0\23\10\215\0\111\10\u03b7\0\3\11"+
    "\65\10\17\11\37\0\12\11\20\0\3\11\55\10\13\11\2\0\1\11"+
    "\22\0\31\10\7\0\12\11\6\0\3\11\44\10\16\11\1\0\12\11"+
    "\100\0\3\11\60\10\16\11\4\10\13\0\12\11\u04a6\0\53\10\15\11"+
    "\10\0\12\11\u0936\0\u036f\10\221\0\143\10\u0b9d\0\u042f\10\u33d1\0\u0239\10"+
    "\u04c7\0\105\10\13\0\1\10\56\11\20\0\4\11\15\10\u4060\0\2\10"+
    "\u2163\0\5\11\3\0\26\11\2\0\7\11\36\0\4\11\224\0\3\11"+
    "\u01bb\0\125\10\1\0\107\10\1\0\2\10\2\0\1\10\2\0\2\10"+
    "\2\0\4\10\1\0\14\10\1\0\1\10\1\0\7\10\1\0\101\10"+
    "\1\0\4\10\2\0\10\10\1\0\7\10\1\0\34\10\1\0\4\10"+
    "\1\0\5\10\1\0\1\10\3\0\7\10\1\0\u0154\10\2\0\31\10"+
    "\1\0\31\10\1\0\37\10\1\0\31\10\1\0\37\10\1\0\31\10"+
    "\1\0\37\10\1\0\31\10\1\0\37\10\1\0\31\10\1\0\10\10"+
    "\2\0\62\11\u1600\0\4\10\1\0\33\10\1\0\2\10\1\0\1\10"+
    "\2\0\1\10\1\0\12\10\1\0\4\10\1\0\1\10\1\0\1\10"+
    "\6\0\1\10\4\0\1\10\1\0\1\10\1\0\1\10\1\0\3\10"+
    "\1\0\2\10\1\0\1\10\2\0\1\10\1\0\1\10\1\0\1\10"+
    "\1\0\1\10\1\0\1\10\1\0\2\10\1\0\1\10\2\0\4\10"+
    "\1\0\7\10\1\0\4\10\1\0\4\10\1\0\1\10\1\0\12\10"+
    "\1\0\21\10\5\0\3\10\1\0\5\10\1\0\21\10\u1144\0\ua6d7\10"+
    "\51\0\u1035\10\13\0\336\10\u3fe2\0\u021e\10\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\u05ee\0"+
    "\1\11\36\0\140\11\200\0\360\11\uffff\0\uffff\0\ufe12\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\1\2\1\1\1\3\1\2\1\4\1\2\1\5"+
    "\1\2\1\0\3\2\2\0\1\2\1\6\1\0\1\7"+
    "\2\0\1\7\1\1";

  private static int [] zzUnpackAction() {
    int [] result = new int[23];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\20\0\40\0\60\0\100\0\20\0\120\0\20"+
    "\0\140\0\160\0\200\0\220\0\240\0\260\0\300\0\320"+
    "\0\20\0\340\0\20\0\360\0\u0100\0\u0110\0\u0110";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[23];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\2\3\3\4\1\5\3\2\1\6\1\7\1\2"+
    "\1\10\1\11\2\2\5\0\12\2\1\0\3\3\15\0"+
    "\5\4\1\12\11\0\1\2\5\0\1\2\1\13\11\2"+
    "\5\0\5\2\1\14\5\2\5\0\2\2\1\15\1\2"+
    "\1\15\5\2\7\0\1\16\10\0\1\13\5\17\1\13"+
    "\1\20\10\13\1\2\5\0\6\2\1\21\4\2\1\22"+
    "\3\0\1\22\2\2\3\15\4\2\1\23\7\16\1\24"+
    "\10\16\7\17\1\25\10\17\1\13\5\17\1\2\1\20"+
    "\10\13\1\0\1\22\3\0\1\22\2\0\3\22\4\0"+
    "\1\26\6\16\1\4\1\24\10\16\6\17\1\27\1\25"+
    "\10\17\20\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[288];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\11\1\1\0\3\1\2\0\2\1\1\0\1\1\2\0"+
    "\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[23];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
	public int line() { return yyline; }
	public int column() { return yycolumn; }

	/**
	 * Nicely named interface to the lexer.
	 */
	public Token nextToken() throws java.io.IOException {
		return yylex();
	}


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Scanner(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2792) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  private final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  private final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  private final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  private final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  private final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  private final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  private final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  private void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private edu.roseHulman.cfg.Token yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            switch (zzLexicalState) {
            case YYINITIAL: {
              return EOFToken.getInstance();
            }
            case 24: break;
            default:
        return null;
        }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { /* ignore */
            }
          case 8: break;
          case 2: 
            { return new TerminalToken(yytext());
            }
          case 9: break;
          case 3: 
            { return OperatorToken.NEWLINE;
            }
          case 10: break;
          case 4: 
            { return EmptyStringToken.getInstance();
            }
          case 11: break;
          case 5: 
            { return OperatorToken.OR;
            }
          case 12: break;
          case 6: 
            { return OperatorToken.GOES_TO;
            }
          case 13: break;
          case 7: 
            { return new NonTerminalToken(yytext());
            }
          case 14: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
