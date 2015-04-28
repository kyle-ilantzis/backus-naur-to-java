package edu.roseHulman.cfg;

/**
 * This immutable class represents semantic actions embedded in a CFG.
 */
public class ActionToken extends Token {

    private final String text;

    public ActionToken(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionToken)) return false;

        ActionToken that = (ActionToken) o;

        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    @Override
    public Token.TokenCategory tokenCategory() {
        return TokenCategory.ACTION;
    }

    @Override
    public int intraCategoryCompareTo(Token other) {
        return this.text.compareTo(((ActionToken) other).text);
    }
}
