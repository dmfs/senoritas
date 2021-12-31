package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class Within<T> implements TestEvent<T>
{
    private final Duration mDuration;
    private final TestEvent<T> mDelegate;


    public Within(Duration duration, TestEvent<T> delegate)
    {
        mDuration = duration;
        mDelegate = delegate;
    }


    @Override
    public Matcher<RxTestAdapter<T>> matcher(TestScheduler scheduler)
    {
        Matcher<RxTestAdapter<T>> delegate = mDelegate.matcher(scheduler);
        return new Matcher<RxTestAdapter<T>>()
        {
            @Override
            public Verdict match(RxTestAdapter<T> actual)
            {
                scheduler.advanceTimeBy(mDuration.toMillis(), TimeUnit.MILLISECONDS);
                scheduler.triggerActions();
                return new MismatchPrepended(new TextDescription("after " + mDuration), delegate.match(actual));
            }


            @Override
            public Description expectation()
            {
                return new Delimited(new TextDescription("after " + mDuration), delegate.expectation());
            }
        };
    }
}
