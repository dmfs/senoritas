package org.saynotobugs.confidence.quality.comparable;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.Satisfies;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class ComparesEqualTo<T extends Comparable<? super T>> extends QualityComposition<T>
{
    /**
     * Creates {@link Quality} that matches if the {@link Comparable} value under test is equal to the given value.
     * <p>
     * Example
     * <pre>
     *     assertThat(1, comparesEqualTo(1));
     * </pre>
     */
    public ComparesEqualTo(T expected)
    {
        super(new Satisfies<>(
            actual -> expected.compareTo(actual) == 0,
            new Delimited(new TextDescription("compares equal to"), new ValueDescription(expected))));
    }
}
