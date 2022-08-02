package org.saynotobugs.confidence.rxjava3.rxexpectation;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.rxjava3.RxExpectationComposition;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class HasNoFurtherValues<T> extends RxExpectationComposition<T>
{
    public HasNoFurtherValues()
    {
        super(scheduler -> new org.saynotobugs.confidence.rxjava3.rxexpectation.internal.HasNoFurtherValues<>());
    }
}
