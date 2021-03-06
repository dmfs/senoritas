package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Fail;


/**
 * A {@link Matcher} that never matches.
 * <p>
 * This may be useful for testing {@link Matcher}s.
 */
@StaticFactories("Core")
public final class Nothing extends MatcherComposition<Object>
{
    /**
     * Creates a {@link Matcher} that never matches.
     */
    public Nothing()
    {
        super(actual -> new Fail(new TextDescription("was something")), new TextDescription("<nothing>"));
    }
}
