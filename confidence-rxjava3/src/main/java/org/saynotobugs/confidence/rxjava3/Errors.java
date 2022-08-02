package org.saynotobugs.confidence.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.InstanceOf;


@StaticFactories("RxJava3")
public final class Errors<T> extends RxExpectationComposition<T>
{
    public Errors(Class<? extends Throwable> error)
    {
        this(new InstanceOf<>(error));
    }


    public Errors(Quality<? super Throwable> errorQuality)
    {
        super(testScheduler -> new org.saynotobugs.confidence.rxjava3.internal.Errors<>(errorQuality));
    }
}
