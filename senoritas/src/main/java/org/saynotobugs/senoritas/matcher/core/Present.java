package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;

import java.util.Optional;


public final class Present<T> extends DelegatingMatcher<Optional<T>>
{
    public Present()
    {
        this(new Anything());
    }


    public Present(T value)
    {
        this(new EqualTo<>(value));
    }


    public Present(Matcher<? super T> delegate)
    {
        super(actual -> actual.isPresent()
                ? new MismatchPrepended(new TextDescription("was present"), delegate.match(actual.get()))
                : new Fail(new TextDescription("was absent")),
            new Delimited(new TextDescription("is present"), delegate.expectation()));
    }
}
