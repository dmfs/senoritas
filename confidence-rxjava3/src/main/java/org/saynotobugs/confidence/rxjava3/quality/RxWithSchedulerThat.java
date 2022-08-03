package org.saynotobugs.confidence.rxjava3.quality;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.quality.AllOfFailingFast;
import org.saynotobugs.confidence.quality.DescriptionUpdated;
import org.saynotobugs.confidence.rxjava3.RxExpectation;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;
import org.saynotobugs.confidence.rxjava3.rxexpectation.ActionTriggering;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class RxWithSchedulerThat<T, RxType> implements Quality<Function<? super TestScheduler, ? extends RxType>>
{
    private final Function<? super RxType, ? extends RxTestAdapter<T>> mTestAdapterFunction;
    private final Function<? super TestScheduler, ? extends Quality<? super RxTestAdapter<T>>> mDelegate;


    public RxWithSchedulerThat(Description description, Function<? super RxType, ? extends RxTestAdapter<T>> testAdapterFunction,
        Iterable<? extends RxExpectation<T>> events)
    {
        mTestAdapterFunction = testAdapterFunction;
        mDelegate = scheduler -> new DescriptionUpdated<>(
            orig -> new Delimited(description, orig),
            new AllOfFailingFast<>(new Mapped<>(event -> new ActionTriggering<>(event).quality(scheduler), events)));
    }


    @Override
    public Assessment assessmentOf(Function<? super TestScheduler, ? extends RxType> candidate)
    {
        TestScheduler scheduler = new TestScheduler();
        return mDelegate.value(scheduler).assessmentOf(mTestAdapterFunction.value(candidate.value(scheduler)));
    }


    @Override
    public Description description()
    {
        return mDelegate.value(new TestScheduler()).description();
    }
}
