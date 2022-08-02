package org.saynotobugs.quality.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;


@StaticFactories("Core")
public final class LessThan<T extends Comparable<T>> extends QualityComposition<T>
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