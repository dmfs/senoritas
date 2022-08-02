package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;


@StaticFactories("RxJava3")
public final class HasNoFurtherValues<T> extends RxExpectationComposition<T>
{
    public HasNoFurtherValues()
    {
        super(scheduler -> new org.saynotobugs.quality.quality.rxjava3.internal.HasNoFurtherValues<>());
    }
}
