package org.saynotobugs.quality.quality.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;


@StaticFactories("Core")
public final class GreaterThanOrEqualTo<T extends Comparable<T>> extends QualityComposition<T>
{
    /**
     * Creates {@link Quality} that matches if the {@link Comparable} value under test is greater than or equal to the given value.
     * <p>
     * Example
     * <pre>
     *     assertThat(1, is(greaterThanOrEqualTo(0)));
     * </pre>
     */
    public GreaterThanOrEqualTo(T expected)
    {
        super(new Satisfies<>(
            actual -> expected.compareTo(actual) <= 0,
            new Delimited(new TextDescription("greater than or equal to"), new ValueDescription(expected))));
    }
}