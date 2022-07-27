package org.saynotobugs.quality.quality.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.utils.ArrayIterable;


@StaticFactories("Core")
public final class ArrayThat extends QualityComposition<Object>
{
    /**
     * Creates a {@link Quality} that matches any array that, when iterated, matches the given {@link Iterable} {@link Quality}.
     */
    @SuppressWarnings("unchecked")
    public <T> ArrayThat(Quality<? super Iterable<? extends T>> delegate)
    {
        super(
            new AllOfFailingFast<>(
                new Satisfies<>(a -> a.getClass().isArray(), ignored -> new TextDescription("not an array"), new TextDescription("an array")),
                new Having<>(new TextDescription("array that"), new TextDescription("array that"), a -> (Iterable<T>) new ArrayIterable(a), delegate)));
    }
}
