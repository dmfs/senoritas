package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;


public final class QuotedDescription implements Description
{
    private final String mEntryString;
    private final Description mDelegate;
    private final String mExitString;


    public QuotedDescription(String quoteString, Description delegate)
    {
        this(quoteString, delegate, quoteString);
    }


    public QuotedDescription(String entryString, Description delegate, String exitString)
    {
        this.mEntryString = entryString;
        this.mDelegate = delegate;
        this.mExitString = exitString;
    }


    @Override
    public void describeTo(Scribe scribe)
    {
        scribe.append(mEntryString);
        mDelegate.describeTo(scribe);
        scribe.append(mExitString);
    }
}
