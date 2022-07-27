package org.saynotobugs.quality.quality.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.TextDescription;


@StaticFactories("Core")
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
        super(new Having<>(new TextDescription("has length"), new TextDescription("had length"), CharSequence::length, delegate));
    }
}
