package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class Within<T> implements Function<TestScheduler, Matcher<T>>
{
    private final Duration mDuration;
    private final Function<? super TestScheduler, ? extends Matcher<? super T>> mDelegate;


    public Within(Duration duration, Function<? super TestScheduler, ? extends Matcher<? super T>> delegate)
    {
        mDuration = duration;
        mDelegate = delegate;
    }


    @Override
    public Matcher<T> value(TestScheduler testScheduler)
    {
        Matcher<? super T> delegate = mDelegate.value(testScheduler);
        return new Matcher<T>()
        {
            @Override
            public Verdict match(T actual)
            {
                testScheduler.advanceTimeBy(mDuration.toMillis(), TimeUnit.MILLISECONDS);
                testScheduler.triggerActions();
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
