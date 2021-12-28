package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class When<T> implements Matcher<RxTestAdapter<T>>, Function<TestScheduler, Matcher<RxTestAdapter<T>>>
{
    private final Description mTriggerDescription;
    private final Runnable mTrigger;
    private final Matcher<? super RxTestAdapter<T>> mDelegate;


    public When(Description triggerDescription, Runnable trigger, Matcher<? super RxTestAdapter<T>> delegate)
    {
        mTriggerDescription = triggerDescription;
        mTrigger = trigger;
        mDelegate = delegate;
    }


    @Override
    public Verdict match(RxTestAdapter<T> actual)
    {
        mTrigger.run();
        return new MismatchPrepended(new Delimited(new TextDescription("when"), mTriggerDescription), mDelegate.match(actual));
    }


    @Override
    public Description expectation()
    {
        return new Delimited(new TextDescription("when"), mTriggerDescription, mDelegate.expectation());
    }


    @Override
    public Matcher<RxTestAdapter<T>> value(TestScheduler scheduler)
    {
        return this;
    }
}
