package org.saynotobugs.quality.quality.core;

import org.dmfs.jems2.single.Reduced;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;


@StaticFactories("Core")
public final class HasNumberOfElements extends QualityComposition<Iterable<?>>
{
    /**
     * Creates a {@link Quality} that matches if the {@link Iterable} under test iterates a number of elements equal to the given value.
     * <pre>
     *     assertThat(asList(1,2,3), hasNumberOfElements(3));
     * </pre>
     */
    public HasNumberOfElements(int size)
    {
        this(new EqualTo<>(size));
    }


    /**
     * Creates a {@link Quality} that matches if the {@link Iterable} under test iterates a number of elements that matches the given {@link Quality}.
     * <pre>
     *     assertThat(asList(1,2,3), hasNumberOfElements(lessThan(4)));
     * </pre>
     */
    public HasNumberOfElements(Quality<? super Integer> delegate)
    {
        super(new ReDescribed<>(
            original -> new Delimited(original, new TextDescription("elements")),
            new Having<>(
                new TextDescription("has"),
                new TextDescription("had"),
                actual -> new Reduced<>(() -> 0, (current, i) -> current + 1, actual).value(), delegate)));
    }
}