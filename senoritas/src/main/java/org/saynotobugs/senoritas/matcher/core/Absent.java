package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.Pass;

import java.util.Optional;


@StaticFactories("Core")
public final class Absent<T> extends MatcherComposition<Optional<T>>
{
    /**
     * Matches empty {@link Optional}s.
     */
    public Absent()
    {
        super(actual -> !actual.isPresent()
                ? new Pass()
                : new Fail(new ValueDescription(actual)),
            new TextDescription("<empty> optional"));
    }
}
