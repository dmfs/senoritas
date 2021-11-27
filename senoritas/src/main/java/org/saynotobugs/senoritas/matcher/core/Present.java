package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.FailPrepended;

import java.util.Optional;


public final class Present<T> extends DelegatingMatcher<Optional<T>>
{
    public Present(T value)
    {
        this(new EqualTo<>(value));
    }


    public Present(Matcher<? super T> delegate)
    {
        super(actual -> actual.isPresent()
                ? new FailPrepended(new TextDescription("was present"), delegate.match(actual.get()))
                : new Fail(new TextDescription("was absent")),
            new Composite(new TextDescription("is present"), delegate.expectation()));
    }
}
