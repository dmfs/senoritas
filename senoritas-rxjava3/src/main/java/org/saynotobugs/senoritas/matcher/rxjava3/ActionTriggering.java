package org.saynotobugs.senoritas.matcher.rxjava3;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


public final class ActionTriggering<T> implements TestEvent<T>
{
    private final TestEvent<T> mDelegate;


    public ActionTriggering(TestEvent<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Matcher<RxTestAdapter<T>> matcher(TestScheduler testScheduler)
    {
        return new Matcher<RxTestAdapter<T>>()
        {
            @Override
            public Verdict match(RxTestAdapter<T> actual)
            {
                testScheduler.triggerActions();
                return mDelegate.matcher(testScheduler).match(actual);
            }


            @Override
            public Description expectation()
            {
                return mDelegate.matcher(testScheduler).expectation();
            }
        };
    }
}
