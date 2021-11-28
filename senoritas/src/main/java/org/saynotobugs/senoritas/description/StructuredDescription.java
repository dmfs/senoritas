package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;
import org.saynotobugs.senoritas.utils.Delimited;


public final class StructuredDescription implements Description
{
    private final String mEntry;
    private final String mDelimiter;
    private final String mExit;
    private final Iterable<? extends Description> mValue;


    public StructuredDescription(String delimiter, Iterable<? extends Description> value)
    {
        this("", delimiter, "", value);
    }


    public StructuredDescription(String entry, String delimiter, String exit, Iterable<? extends Description> value)
    {
        mEntry = entry;
        mDelimiter = delimiter;
        mExit = exit;
        mValue = value;
    }


    @Override
    public void describeTo(Scribe scribe)
    {
        Scribe s = (mEntry.length() + mExit.length() > 0) ? scribe.indented() : scribe;
        new Delimited<Description>(
            () -> {
                if (mEntry.length() != 0)
                {
                    s.append(mEntry).newLine();
                }
            },
            () -> s.append(mDelimiter).newLine(),
            () -> {
                if (mExit.length() != 0)
                {
                    scribe.newLine().append(mExit);
                }
            },
            e -> e.describeTo(s)).process(mValue);
    }
}
