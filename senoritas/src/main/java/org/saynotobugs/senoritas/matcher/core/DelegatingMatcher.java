package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.Function;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;


public abstract class DelegatingMatcher<T> implements Matcher<T>
{
    private final Matcher<T> mDelegate;


    public DelegatingMatcher(Function<? super T, ? extends Verdict> testFunction, Description expectation)
    {
        this(new Matcher<T>()
        {
            @Override
            public Verdict match(T actual)
            {
                return testFunction.value(actual);
            }


            @Override
            public Description expectation()
            {
                return expectation;
            }
        });
    }


    public DelegatingMatcher(Matcher<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final Verdict match(T actual)
    {
        return mDelegate.match(actual);
    }


    @Override
    public final Description expectation()
    {
        return mDelegate.expectation();
    }
}
