package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.schedulers.TestScheduler;


public final class After<T> implements Function<TestScheduler, Matcher<T>>
{
    private final Duration mDuration;
    private final Matcher<? super T> mDelegate;


    public After(Duration duration, Matcher<? super T> delegate)
    {
        mDuration = duration;
        mDelegate = delegate;
    }


    @Override
    public Matcher<T> value(TestScheduler testScheduler)
    {
        return new Matcher<T>()
        {
            @Override
            public Verdict match(T actual)
            {
                testScheduler.advanceTimeBy(mDuration.toMillis(), TimeUnit.MILLISECONDS);
                testScheduler.triggerActions();
                return new MismatchPrepended(new TextDescription("after " + mDuration), mDelegate.match(actual));
            }


            @Override
            public Description expectation()
            {
                return new Delimited(new TextDescription("after " + mDuration), mDelegate.expectation());
            }
        };
    }
}
