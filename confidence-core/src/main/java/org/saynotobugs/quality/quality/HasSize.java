package org.saynotobugs.quality.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.TextDescription;

import java.util.Collection;


@StaticFactories("Core")
public final class HasSize extends QualityComposition<Collection<?>>
{
    /**
     * Creates a {@link Quality} that matches if size of the {@link Collection} under test is equal to the given value.
     * <pre>
     *     assertThat(asList(1,2,3), hasSize(3));
     * </pre>
     */
    public HasSize(int size)
    {
        this(new EqualTo<>(size));
    }


    /**
     * Creates a {@link Quality} that matches if the size {@link Collection} under test matches the given {@link Quality}.
     * <pre>
     *     assertThat(asList(1,2,3), hasSize(lessThan(4)));
     * </pre>
     */
    public HasSize(Quality<? super Integer> delegate)
    {
        super(new Having<>(new TextDescription("has size"), new TextDescription("had size"), Collection::size, delegate));
    }
}
