package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;


@StaticFactories("RxJava3")
public final class HasNoFurtherValues<T> extends RxExpectationComposition<T>
{
    public HasNoFurtherValues()
    {
        super(scheduler -> new org.saynotobugs.senoritas.matcher.rxjava3.internal.HasNoFurtherValues<>());
    }
}
