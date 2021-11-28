package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;


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
        super(new QuotedDescription(
            "\"",
            scribe -> scribe.append(value.toString()
                .replace("\\", "\\\\")
                .replace("\t", "\\t")
                .replace("\b", "\\b")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\f", "\\f")
                .replace("\"", "\\\""))));
    }
}
