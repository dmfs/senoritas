package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;

import static org.saynotobugs.senoritas.description.LiteralDescription.DQUOTES;


/**
 * The {@link Description} of a {@link CharSequence} value.
 */
public final class CharSequenceDescription extends DelegatingDescription
{
    /**
     * Creates a {@link Description} for the given {@link CharSequence}.
     */
    public CharSequenceDescription(CharSequence value)
    {
        super(new QuotedDescription(DQUOTES, new TextDescription(value)));
    }
}
