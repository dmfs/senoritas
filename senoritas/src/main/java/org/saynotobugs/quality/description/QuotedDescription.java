package org.saynotobugs.quality.description;

import org.saynotobugs.quality.Description;


/**
 * A {@link Description} that's put in some sort of quoting characters.
 */
public final class QuotedDescription extends DescriptionComposition
{

    public QuotedDescription(String quoteString, Description delegate)
    {
        this(quoteString, delegate, quoteString);
    }


    public QuotedDescription(String entry, Description delegate, String exit)
    {
        this(new TextDescription(entry), delegate, new TextDescription(exit));
    }


    public QuotedDescription(Description quoteString, Description delegate)
    {
        this(quoteString, delegate, quoteString);
    }


    public QuotedDescription(Description entry, Description delegate, Description exit)
    {
        super(new Composite(entry, delegate, exit));
    }
}
