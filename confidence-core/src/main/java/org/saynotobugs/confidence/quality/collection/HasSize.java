package org.saynotobugs.confidence.quality.collection;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.object.EqualTo;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.composite.Having;

import java.util.Collection;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
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
