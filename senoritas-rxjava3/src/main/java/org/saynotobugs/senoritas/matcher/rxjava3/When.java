package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.function.DelegatingFunction;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class When<T> extends DelegatingFunction<TestScheduler, Matcher<RxTestAdapter<? extends T>>>
{

    public When(Description triggerDescription, Runnable trigger, Function<? super TestScheduler, ? extends Matcher<? super RxTestAdapter<? extends T>>> delegate)
    {
        super(testScheduler -> new Matcher<RxTestAdapter<? extends T>>()
        {
            @Override
            public Verdict match(RxTestAdapter<? extends T> actual)
            {
                trigger.run();
                return new MismatchPrepended(
                    new Delimited(new TextDescription("when"), triggerDescription),
                    new ActionTriggering<>(delegate).value(testScheduler).match(actual));
            }


            @Override
            public Description expectation()
            {
                return new Delimited(new TextDescription("when"), triggerDescription, delegate.value(testScheduler).expectation());
            }
        });
    }
}
