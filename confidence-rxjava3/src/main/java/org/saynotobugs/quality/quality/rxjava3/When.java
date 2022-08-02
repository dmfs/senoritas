package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.assessment.FailPrepended;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;


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
        super(testScheduler -> new Quality<RxTestAdapter<T>>()
        {
            @Override
            public Assessment assessmentOf(RxTestAdapter<T> candidate)
            {
                trigger.run();
                return new FailPrepended(
                    new Delimited(new TextDescription("when"), triggerDescription),
                    new ActionTriggering<>(delegate).quality(testScheduler).assessmentOf(candidate));
            }


            @Override
            public Description description()
            {
                return new Delimited(new TextDescription("when"), triggerDescription, delegate.quality(testScheduler).description());
            }
        });
    }
}
