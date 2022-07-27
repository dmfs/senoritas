package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.quality.core.InstanceOf;


@StaticFactories("RxJava3")
public final class Errors<T> extends RxExpectationComposition<T>
{
    public Errors(Class<? extends Throwable> error)
    {
        this(new InstanceOf<>(error));
    }


    public Errors(Quality<? super Throwable> errorQuality)
    {
        super(testScheduler -> new org.saynotobugs.quality.quality.rxjava3.internal.Errors<>(errorQuality));
    }
}
