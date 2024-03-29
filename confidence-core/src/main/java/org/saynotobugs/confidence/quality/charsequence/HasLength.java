package org.saynotobugs.confidence.quality.charsequence;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.composite.Has;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.EqualTo;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class HasLength extends QualityComposition<CharSequence>
{
    /**
     * Creates a {@link Quality} that matches if length of the {@link CharSequence} under test is equal to the given value.
     * <pre>
     *     assertThat("123", hasLength(3));
     * </pre>
     */
    public HasLength(int size)
    {
        this(new EqualTo<>(size));
    }


    /**
     * Creates a {@link Quality} that matches if the length {@link CharSequence} under test matches the given {@link Quality}.
     * <pre>
     *     assertThat("123", hasLength(lessThan(4)));
     * </pre>
     */
    public HasLength(Quality<? super Integer> delegate)
    {
        super(new Has<>("length", CharSequence::length, delegate));
    }
}
