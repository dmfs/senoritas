package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Fail;


/**
 * A {@link Matcher} that never matches.
 * <p>
 * This may be useful for testing {@link Matcher}s.
 */
public final class Nothing extends DelegatingMatcher<Object>
{
    public Nothing()
    {
        super(actual -> new Fail(new TextDescription("was something")), new TextDescription("<nothing>"));
    }
}
