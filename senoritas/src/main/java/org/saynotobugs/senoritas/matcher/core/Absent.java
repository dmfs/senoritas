package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.description.TextDescription;

import java.util.Optional;


@StaticFactories("Core")
public final class Absent<T> extends MatcherComposition<Optional<T>>
{
    /**
     * Matches empty {@link Optional}s.
     */
    public Absent()
    {
        super(new Satisfies<>(
            actual -> !actual.isPresent(),
            new TextDescription("<empty> optional")));
    }
}
