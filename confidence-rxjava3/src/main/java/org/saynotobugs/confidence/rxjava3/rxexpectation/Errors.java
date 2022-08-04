package org.saynotobugs.confidence.rxjava3.rxexpectation;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.object.InstanceOf;
import org.saynotobugs.confidence.rxjava3.RxExpectationComposition;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class Errors<T> extends RxExpectationComposition<T>
{
    public Errors(Class<? extends Throwable> error)
    {
        this(new InstanceOf<>(error));
    }


    public Errors(Quality<? super Throwable> errorQuality)
    {
        super(testScheduler -> new org.saynotobugs.confidence.rxjava3.rxexpectation.internal.Errors<>(errorQuality));
    }
}
