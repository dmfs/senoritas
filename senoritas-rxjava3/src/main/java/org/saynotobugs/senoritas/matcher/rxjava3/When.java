package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestAdapter;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;


@StaticFactories("RxJava3")
public final class When<T> extends RxExpectationComposition<T>
{

    /**
     * Creates an {@link RxExpectation} that runs the given {@link Runnable} before delegating to the given {@link RxExpectation}.
     */
    public When(Runnable trigger, RxExpectation<T> delegate)
    {
        this("triggered", trigger, delegate);
    }


    /**
     * Creates an {@link RxExpectation} that runs the given {@link Runnable} before delegating to the given {@link RxExpectation}.
     */
    public When(String triggerDescription, Runnable trigger, RxExpectation<T> delegate)
    {
        this(new TextDescription(triggerDescription), trigger, delegate);
    }


    /**
     * Creates an {@link RxExpectation} that runs the given {@link Runnable} before delegating to the given {@link RxExpectation}.
     */
    public When(Description triggerDescription, Runnable trigger, RxExpectation<T> delegate)
    {
        super(testScheduler -> new Matcher<RxTestAdapter<T>>()
        {
            @Override
            public Verdict match(RxTestAdapter<T> actual)
            {
                trigger.run();
                return new MismatchPrepended(
                    new Delimited(new TextDescription("when"), triggerDescription),
                    new ActionTriggering<>(delegate).matcher(testScheduler).match(actual));
            }


            @Override
            public Description expectation()
            {
                return new Delimited(new TextDescription("when"), triggerDescription, delegate.matcher(testScheduler).expectation());
            }
        });
    }
}
