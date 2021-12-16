package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.hamcrest.StringDescription;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.Pass;


@StaticFactories("Core")
public final class Hamcrest<T> implements Matcher<T>
{
    private final org.hamcrest.Matcher<? super T> mDelegate;


    public Hamcrest(org.hamcrest.Matcher<? super T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Verdict match(T actual)
    {
        if (mDelegate.matches(actual))
        {
            return new Pass();
        }
        org.hamcrest.Description mismatch = new StringDescription();
        mDelegate.describeMismatch(actual, mismatch);
        return new Fail(new TextDescription(mismatch.toString()));
    }


    @Override
    public Description expectation()
    {
        org.hamcrest.Description expected = new StringDescription();
        mDelegate.describeTo(expected);
        return new TextDescription(expected.toString());
    }

}
