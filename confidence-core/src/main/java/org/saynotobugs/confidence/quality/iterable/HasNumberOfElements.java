package org.saynotobugs.confidence.quality.iterable;

import org.dmfs.jems2.single.Reduced;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.DescribedAs;
import org.saynotobugs.confidence.quality.composite.Having;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.EqualTo;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
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
        super(new DescribedAs<>(
            original -> new Delimited(original, new TextDescription("elements")),
            new Having<>(
                d->d,
                d->d,
                actual -> new Reduced<>(() -> 0, (current, i) -> current + 1, actual).value(), delegate)));
    }
}
