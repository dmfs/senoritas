package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;

import java.util.Optional;


@StaticFactories("Core")
public final class Present<T> extends MatcherComposition<Optional<T>>
{
    /**
     * Matches present {@link Optional}s ith any value.
     */
    public Present()
    {
        this(new Anything());
    }


    /**
     * Matches present {@link Optional}s with a value that's equal to the given one.
     */
    public Present(T value)
    {
        this(new EqualTo<>(value));
    }


    /**
     * Matches present {@link Optional}s with a value that matches the given matcher.
     */
    public Present(Matcher<? super T> delegate)
    {
        super(actual -> actual.isPresent()
                ? new MismatchPrepended(new TextDescription("was present"), delegate.match(actual.get()))
                : new Fail(new TextDescription("was absent")),
            new Delimited(new TextDescription("is present"), delegate.expectation()));
    }
}
