package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.quality.core.AllOfFailingFast;
import org.saynotobugs.quality.quality.core.ReDescribed;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class RxWithSchedulerThat<T, RxType> implements Quality<Function<? super TestScheduler, ? extends RxType>>
{
    private final Function<? super RxType, ? extends RxTestAdapter<T>> mTestAdapterFunction;
    private final Function<? super TestScheduler, ? extends Quality<? super RxTestAdapter<T>>> mDelegate;


    public RxWithSchedulerThat(Description description, Function<? super RxType, ? extends RxTestAdapter<T>> testAdapterFunction,
        Iterable<? extends RxExpectation<T>> events)
    {
        mTestAdapterFunction = testAdapterFunction;
        mDelegate = scheduler -> new ReDescribed<>(
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
