package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.verdict.MismatchUpdated;


@StaticFactories("Core")
public final class ReDescribed<T> extends MatcherComposition<T>
{
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
        super(
            actual -> new MismatchUpdated(mismatchUpdate, delegate.match(actual)),
            expectationUpdate.value(delegate.expectation()));
    }
}
