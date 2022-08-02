package org.saynotobugs.confidence.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;


@StaticFactories("RxJava3")
public final class EmitsNothing<T> extends RxExpectationComposition<T>
{
    public EmitsNothing()
    {
        super(testScheduler -> new org.saynotobugs.confidence.rxjava3.internal.EmitsNothing<>());
    }
}
