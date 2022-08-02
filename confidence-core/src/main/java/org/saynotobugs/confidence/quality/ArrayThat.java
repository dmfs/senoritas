package org.saynotobugs.confidence.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.utils.ArrayIterable;


@StaticFactories("Core")
public final class ArrayThat extends QualityComposition<Object>
{
    /**
     * Creates a {@link Quality} that matches any array that, when iterated, matches the given {@link Iterable} {@link Quality}.
     */
    @SuppressWarnings("unchecked")
    public <T> ArrayThat(Quality<? super Iterable<T>> delegate)
    {
        super(
            new AllOfFailingFast<>(
                new Satisfies<>(a -> a.getClass().isArray(), ignored -> new TextDescription("not an array"), new TextDescription("an array")),
                new Having<>(new TextDescription("array that"), new TextDescription("array"), a -> (Iterable<T>) new ArrayIterable(a), delegate)));
    }
}
