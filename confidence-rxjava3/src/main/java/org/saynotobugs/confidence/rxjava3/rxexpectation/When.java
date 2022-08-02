package org.saynotobugs.confidence.rxjava3.rxexpectation;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.FailPrepended;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.rxjava3.RxExpectation;
import org.saynotobugs.confidence.rxjava3.RxExpectationComposition;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
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
