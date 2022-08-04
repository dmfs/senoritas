package org.saynotobugs.confidence.rxjava3.rxexpectation;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.DescribedAs;
import org.saynotobugs.confidence.quality.composite.Having;
import org.saynotobugs.confidence.quality.composite.NoneOf;
import org.saynotobugs.confidence.quality.iterable.Contains;
import org.saynotobugs.confidence.quality.trivial.Anything;
import org.saynotobugs.confidence.rxjava3.RxExpectation;
import org.saynotobugs.confidence.rxjava3.RxExpectationComposition;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;
import org.saynotobugs.confidence.rxjava3.rxexpectation.internal.IsCancelled;
import org.saynotobugs.confidence.rxjava3.rxexpectation.internal.IsComplete;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class IsAlive<T> extends RxExpectationComposition<T>
{
    /**
     * Returns an {@link RxExpectation} that expects a {@link RxTestAdapter} that's still alive,
     * i.e. did not complete, did not error nor has been cancelled.
     */
    public IsAlive()
    {
        super(testScheduler -> new DescribedAs<>(
            orig -> orig,
            orig -> new TextDescription("alive"),
            new NoneOf<>(
                new Having<>("error", RxTestAdapter::errors, new Contains<>(new Anything())),
                new IsComplete(),
                new IsCancelled<>()
            )));
    }
}
