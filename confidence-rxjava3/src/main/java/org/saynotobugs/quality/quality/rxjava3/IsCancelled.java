package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;


@StaticFactories("RxJava3")
public final class IsCancelled<T> extends RxExpectationComposition<T>
{
    public IsCancelled()
    {
        super(testScheduler -> new org.saynotobugs.quality.quality.rxjava3.internal.IsCancelled<>());
    }
}
