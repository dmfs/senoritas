package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.assessment.FailPrepended;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class Within<T> implements RxExpectation<T>
{
    private final Duration mDuration;
    private final RxExpectation<T> mDelegate;


    /**
     * Creates an {@link RxExpectation} that expects the given {@link RxExpectation} to match within the given {@link Duration}.
     * <p>
     * It does so by forwarding the {@link TestScheduler} by the given {@link Duration} and delegating to the given {@link RxExpectation}.
     */
    public Within(Duration duration, RxExpectation<T> delegate)
    {
        if (duration.isNegative())
        {
            throw new IllegalArgumentException("Unsupported negative duration: " + duration);
        }
        mDuration = duration;
        mDelegate = delegate;
    }


    @Override
    public Quality<RxTestAdapter<T>> quality(TestScheduler scheduler)
    {
        Quality<RxTestAdapter<T>> delegate = mDelegate.quality(scheduler);
        return new Quality<RxTestAdapter<T>>()
        {
            @Override
            public Assessment assessmentOf(RxTestAdapter<T> candidate)
            {
                scheduler.advanceTimeBy(mDuration.toMillis(), TimeUnit.MILLISECONDS);
                scheduler.triggerActions();
                return new FailPrepended(new TextDescription("after " + mDuration), delegate.assessmentOf(candidate));
            }


            @Override
            public Description description()
            {
                return new Delimited(new TextDescription("after " + mDuration), delegate.description());
            }
        };
    }
}
