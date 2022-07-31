package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.core.*;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;
import org.saynotobugs.quality.quality.rxjava3.internal.IsCancelled;
import org.saynotobugs.quality.quality.rxjava3.internal.IsComplete;


@StaticFactories("RxJava3")
public final class IsAlive<T> extends RxExpectationComposition<T>
{
    /**
     * Returns an {@link RxExpectation} that expects a {@link RxTestAdapter} that's still alive,
     * i.e. did not complete, did not error nor has been cancelled.
     */
    public IsAlive()
    {
        super(testScheduler -> new ReDescribed<>(
            orig -> orig,
            orig -> new TextDescription("alive"),
            new NoneOf<>(
                new Having<>("error", RxTestAdapter::errors, new Contains<>(new Anything())),
                new IsComplete(),
                new IsCancelled<>()
            )));
    }
}