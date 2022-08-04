package org.saynotobugs.confidence.quality.comparable;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.Satisfies;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class LessThan<T extends Comparable<? super T>> extends QualityComposition<T>
{

    /**
     * Creates {@link Quality} that matches if the {@link Comparable} value under test is less than the given value.
     * <p>
     * Example
     * <pre>
     *     assertThat(0, is(lessThan(1)));
     * </pre>
     */
    public LessThan(T expected)
    {
        super(new Satisfies<>(
            actual -> expected.compareTo(actual) > 0,
            new Delimited(new TextDescription("less than"), new ValueDescription(expected))));
    }
}