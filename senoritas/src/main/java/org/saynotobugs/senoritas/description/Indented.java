package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;


/**
 * A {@link Description} that increases the level of indentation by one.
 */
public final class Indented extends DelegatingDescription
{
    public Indented(Description delegate)
    {
        super(scribe -> delegate.describeTo(scribe.indented()));
    }
}
