package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;


/**
 * {@link Description} of a {@code null} value.
 */
public final class NullDescription implements Description
{
    public final static Description nullDescription = new NullDescription();


    @Override
    public void describeTo(Scribe scribe)
    {
        scribe.append("<null>");
    }
}
