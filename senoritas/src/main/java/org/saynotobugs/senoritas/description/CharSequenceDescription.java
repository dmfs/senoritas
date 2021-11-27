package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;


/**
 * The {@link Description} of a {@link CharSequence} value.
 */
public final class CharSequenceDescription implements Description
{
    private final CharSequence mValue;


    /**
     * Creates a {@link Description} for the given {@link CharSequence}.
     */
    public CharSequenceDescription(CharSequence value)
    {
        mValue = value;
    }


    @Override
    public void describeTo(Scribe scribe)
    {
        scribe.append("\"")
            .append(mValue.toString()
                .replace("\\", "\\\\")
                .replace("\t", "\\t")
                .replace("\b", "\\b")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\f", "\\f")
                .replace("\"", "\\\""))
            .append("\"");
    }
}
