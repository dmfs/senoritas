package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;

import io.reactivex.rxjava3.schedulers.TestScheduler;


public final class ActionTriggering<T> implements Function<TestScheduler, Matcher<T>>
{
    private final Function<? super TestScheduler, ? extends Matcher<? super T>> mDelegate;


    public ActionTriggering(Function<? super TestScheduler, ? extends Matcher<? super T>> delegate)
    {
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
                testScheduler.triggerActions();
                return mDelegate.value(testScheduler).match(actual);
            }


            @Override
            public Description expectation()
            {
                return mDelegate.value(testScheduler).expectation();
            }
        };
    }
}
