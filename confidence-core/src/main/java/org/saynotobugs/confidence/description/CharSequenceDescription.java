package org.saynotobugs.confidence.description;

import org.saynotobugs.confidence.Description;

import static org.saynotobugs.confidence.description.LiteralDescription.DQUOTES;


/**
 * The {@link Description} of a {@link CharSequence} value. This is similar to {@link TextDescription} but values will be quoted with double quotes.
 */
public final class CharSequenceDescription extends DescriptionComposition
{
    /**
     * Creates a {@link Description} for the given {@link CharSequence}.
     */
    public CharSequenceDescription(CharSequence value)
    {
        super(new QuotedDescription(DQUOTES, new TextDescription(value)));
    }
}
