package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Mapped;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.matcher.core.AllOfFailingFast;
import org.saynotobugs.senoritas.matcher.core.ReDescribed;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


public final class RxWithSchedulerThat<T, RxType> implements Matcher<Function<? super TestScheduler, ? extends RxType>>
{
    private final Function<? super RxType, ? extends RxTestAdapter<T>> mTestAdapterFunction;
    private final Function<? super TestScheduler, ? extends Matcher<? super RxTestAdapter<T>>> mDelegate;


    public RxWithSchedulerThat(Description description, Function<? super RxType, ? extends RxTestAdapter<T>> testAdapterFunction,
        Iterable<? extends TestEvent<T>> events)
    {
        mTestAdapterFunction = testAdapterFunction;
        mDelegate = scheduler -> new ReDescribed<>(
            orig -> new Delimited(description, orig),
            new AllOfFailingFast<>(new Mapped<>(event -> new ActionTriggering<>(event).matcher(scheduler), events)));
    }


    @Override
    public Verdict match(Function<? super TestScheduler, ? extends RxType> actual)
    {
        TestScheduler scheduler = new TestScheduler();
        return mDelegate.value(scheduler).match(mTestAdapterFunction.value(actual.value(scheduler)));
    }


    @Override
    public Description expectation()
    {
        return mDelegate.value(new TestScheduler()).expectation();
    }
}
