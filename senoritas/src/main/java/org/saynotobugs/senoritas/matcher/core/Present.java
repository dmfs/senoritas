package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Updated;
import org.saynotobugs.senoritas.verdict.Fail;

import java.util.Optional;


public final class Present<T> implements Matcher<Optional<T>>
{
    private final Matcher<? super T> delegate;


    public Present(T value)
    {
        this(new EqualTo<>(value));
    }


    public Present(Matcher<? super T> delegate)
    {
        this.delegate = delegate;
    }


    @Override
    public Verdict match(Optional<T> actual)
    {
        return actual.isPresent()
            ? new Updated(orig -> new Composite(new TextDescription("was present"), orig), delegate.match(actual.get()))
            : new Fail(new TextDescription("was absent"));
    }


    @Override
    public Description expectation()
    {
        return new Composite(new TextDescription("is present"), delegate.expectation());
    }

}
