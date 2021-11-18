package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;


public final class Is<T> implements Matcher<T>
{
    private final Matcher<? super T> mDelegate;


    public Is(T delegate)
    {
        this(new EqualTo<>(delegate));
    }


    public Is(Matcher<? super T> delegate)
    {
        this.mDelegate = delegate;
    }


    @Override
    public Verdict match(T actual)
    {
        return mDelegate.match(actual);
    }


    @Override
    public Description expectation()
    {
        return mDelegate.expectation();
    }

}
