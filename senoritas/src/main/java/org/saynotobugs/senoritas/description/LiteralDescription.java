package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;


public final class LiteralDescription extends DescriptionComposition
{
    public static final Description DQUOTES = new LiteralDescription("\"");
    public static final Description COMMA = new LiteralDescription(",");
    public static final Description SPACE = new LiteralDescription(" ");
    public static final Description EMPTY = scribe -> {};
    public static final Description NEW_LINE = Scribe::newLine;
    public static final Description NULL = new LiteralDescription("<null>");
    public static final Description COMMA_NEW_LINE = new Composite(COMMA, NEW_LINE);


    /**
     * This class is not supposed to be used directly.
     */
    private LiteralDescription(CharSequence text)
    {
        super(scribe -> scribe.append(text));
    }
}
