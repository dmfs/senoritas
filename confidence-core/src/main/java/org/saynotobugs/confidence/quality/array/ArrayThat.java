package org.saynotobugs.confidence.quality.array;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.AllOfFailingFast;
import org.saynotobugs.confidence.quality.composite.Has;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.Satisfies;
import org.saynotobugs.confidence.utils.ArrayIterable;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class ArrayThat extends QualityComposition<Object>
{
    /**
     * A {@link Quality} of an array that, when iterated, has the given {@link Iterable} {@link Quality}.
     */
    @SuppressWarnings("unchecked")
    public <T> ArrayThat(Quality<? super Iterable<T>> delegate)
    {
        super(
            new AllOfFailingFast<>(
                new Satisfies<>(a -> a.getClass().isArray(), ignored -> new TextDescription("not an array"), new TextDescription("an array")),
                new Has<>(new TextDescription("array that"), new TextDescription("array"), a -> (Iterable<T>) new ArrayIterable(a), delegate)));
    }
}
