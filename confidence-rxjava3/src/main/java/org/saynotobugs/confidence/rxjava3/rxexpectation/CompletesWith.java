package org.saynotobugs.confidence.rxjava3.rxexpectation;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.AllOf;
import org.saynotobugs.confidence.quality.Iterates;
import org.saynotobugs.confidence.rxjava3.RxExpectationComposition;
import org.saynotobugs.confidence.rxjava3.rxexpectation.internal.Emits;
import org.saynotobugs.confidence.rxjava3.rxexpectation.internal.EmitsNothing;
import org.saynotobugs.confidence.rxjava3.rxexpectation.internal.IsComplete;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class CompletesWith<T> extends RxExpectationComposition<T>
{
    @SafeVarargs
    public CompletesWith(T... values)
    {
        this(values.length, new Iterates<>(values));
    }


    @SafeVarargs
    public CompletesWith(Quality<? super T>... values)
    {
        this(values.length, new Iterates<>(values));
    }


    public CompletesWith(int elementCount, Quality<? super Iterable<T>> values)
    {
        super(testScheduler -> new AllOf<>(
            new IsComplete(),
            new Emits<>(elementCount, values),
            new EmitsNothing<>()
        ));
    }
}
