package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.Iterates;
import org.saynotobugs.quality.quality.rxjava3.internal.Emits;
import org.saynotobugs.quality.quality.rxjava3.internal.EmitsNothing;
import org.saynotobugs.quality.quality.rxjava3.internal.IsComplete;


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
