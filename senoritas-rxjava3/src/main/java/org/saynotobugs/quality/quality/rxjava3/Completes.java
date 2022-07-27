package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.quality.core.AllOf;
import org.saynotobugs.quality.quality.rxjava3.internal.EmitsNothing;


@StaticFactories("RxJava3")
public final class Completes<T> extends RxExpectationComposition<T>
{
    public Completes()
    {
        super(testScheduler ->
            new AllOf<>(
                new org.saynotobugs.quality.quality.rxjava3.internal.IsComplete(),
                new EmitsNothing<>()));
    }
}
