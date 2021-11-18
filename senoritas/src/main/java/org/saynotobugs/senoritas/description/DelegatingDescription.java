package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;


public abstract class DelegatingDescription implements Description
{
    private final Description mDelegate;


    public DelegatingDescription(Description delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final void describeTo(Scribe sink)
    {
        mDelegate.describeTo(sink);
    }
}
