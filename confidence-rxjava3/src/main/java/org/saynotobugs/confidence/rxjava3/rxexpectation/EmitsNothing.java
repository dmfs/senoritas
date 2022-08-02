package org.saynotobugs.confidence.rxjava3.rxexpectation;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.rxjava3.RxExpectationComposition;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class EmitsNothing<T> extends RxExpectationComposition<T>
{
    public EmitsNothing()
    {
        super(testScheduler -> new org.saynotobugs.confidence.rxjava3.rxexpectation.internal.EmitsNothing<>());
    }
}
