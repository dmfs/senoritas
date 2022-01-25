package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.verdict.MismatchUpdated;


@StaticFactories("Core")
public final class ReDescribed<T> implements Matcher<T>
{
    private final Function<Description, ? extends Description> mMismatchUpdate;
    private final Function<Description, ? extends Description> mExpectationUpdate;
    private final Matcher<T> mDelegate;


    public ReDescribed(
        Function<Description, ? extends Description> descriptionUpdate,
        Matcher<T> delegate)
    {
        this(descriptionUpdate, descriptionUpdate, delegate);
    }


    public ReDescribed(
        Function<Description, ? extends Description> mismatchUpdate,
        Function<Description, ? extends Description> expectationUpdate,
        Matcher<T> delegate)
    {
        this.mMismatchUpdate = mismatchUpdate;
        this.mExpectationUpdate = expectationUpdate;
        this.mDelegate = delegate;
    }


    @Override
    public Verdict match(T actual)
    {
        return new MismatchUpdated(mMismatchUpdate, mDelegate.match(actual));
    }


    @Override
    public Description expectation()
    {
        return mExpectationUpdate.value(mDelegate.expectation());
    }
}
