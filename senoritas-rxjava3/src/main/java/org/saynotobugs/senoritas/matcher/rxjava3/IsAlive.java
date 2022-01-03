package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.*;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestAdapter;
import org.saynotobugs.senoritas.matcher.rxjava3.internal.IsCancelled;
import org.saynotobugs.senoritas.matcher.rxjava3.internal.IsComplete;


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
