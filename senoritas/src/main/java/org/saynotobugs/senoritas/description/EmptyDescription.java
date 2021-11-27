package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;


public final class EmptyDescription implements Description
{
    public static final Description emptyDescription = new EmptyDescription();


    @Override
    public void describeTo(Scribe scribe)
    {
        // nothing
    }
}
