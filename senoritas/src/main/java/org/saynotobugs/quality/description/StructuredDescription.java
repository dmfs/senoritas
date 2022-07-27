package org.saynotobugs.quality.description;

import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Scribe;
import org.saynotobugs.quality.utils.Delimited;

import static org.saynotobugs.quality.description.LiteralDescription.EMPTY;


/**
 * A generic {@link Description} for structured values.
 */
public final class StructuredDescription implements Description
{
    private final Description mEntry;
    private final Description mDelimiter;
    private final Description mExit;
    private final Iterable<? extends Description> mValue;


    public StructuredDescription(CharSequence delimiter, Iterable<? extends Description> value)
    {
        this(EMPTY, new TextDescription(delimiter), EMPTY, value);
    }


    public StructuredDescription(Description delimiter, Iterable<? extends Description> value)
    {
        this(EMPTY, delimiter, EMPTY, value);
    }


    public StructuredDescription(String entry, String delimiter, String exit, Iterable<? extends Description> value)
    {
        this(new TextDescription(entry), new TextDescription(delimiter), new TextDescription(exit), value);
    }


    public StructuredDescription(Description entry, Description delimiter, Description exit, Iterable<? extends Description> value)
    {
        mEntry = entry;
        mDelimiter = delimiter;
        mExit = exit;
        mValue = value;
    }


    @Override
    public void describeTo(Scribe scribe)
    {
        Scribe s = scribe.indented();
        new Delimited<Description>(
            () -> mEntry.describeTo(scribe),
            () -> mDelimiter.describeTo(s),
            () -> mExit.describeTo(scribe),
            e -> e.describeTo(s)).process(mValue);
    }
}
