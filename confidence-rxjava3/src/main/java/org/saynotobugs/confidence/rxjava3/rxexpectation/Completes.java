package org.saynotobugs.confidence.rxjava3.rxexpectation;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.rxjava3.RxExpectationComposition;
import org.saynotobugs.confidence.rxjava3.rxexpectation.internal.EmitsNothing;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class Completes<T> extends RxExpectationComposition<T>
{
    public Completes()
    {
        super(testScheduler ->
            new AllOf<>(
                new org.saynotobugs.confidence.rxjava3.rxexpectation.internal.IsComplete(),
                new EmitsNothing<>()));
    }
}
