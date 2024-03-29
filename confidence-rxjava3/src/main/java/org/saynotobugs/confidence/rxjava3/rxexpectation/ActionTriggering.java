package org.saynotobugs.confidence.rxjava3.rxexpectation;

import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.rxjava3.RxExpectation;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


/**
 * A decorator to an {@link RxExpectation} that triggers the actions of the {@link TestScheduler} before delegating to the {@link Quality}
 * returned by the decorated {@link RxExpectation}.
 */
public final class ActionTriggering<T> implements RxExpectation<T>
{
    private final RxExpectation<T> mDelegate;


    public ActionTriggering(RxExpectation<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Quality<RxTestAdapter<T>> quality(TestScheduler testScheduler)
    {
        return new Quality<RxTestAdapter<T>>()
        {
            @Override
            public Assessment assessmentOf(RxTestAdapter<T> candidate)
            {
                testScheduler.triggerActions();
                return mDelegate.quality(testScheduler).assessmentOf(candidate);
            }


            @Override
            public Description description()
            {
                return mDelegate.quality(testScheduler).description();
            }
        };
    }
}
