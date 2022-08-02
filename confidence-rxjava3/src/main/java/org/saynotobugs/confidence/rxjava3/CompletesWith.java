package org.saynotobugs.confidence.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.AllOf;
import org.saynotobugs.confidence.quality.Iterates;
import org.saynotobugs.confidence.rxjava3.internal.Emits;
import org.saynotobugs.confidence.rxjava3.internal.EmitsNothing;
import org.saynotobugs.confidence.rxjava3.internal.IsComplete;


@StaticFactories("RxJava3")
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
