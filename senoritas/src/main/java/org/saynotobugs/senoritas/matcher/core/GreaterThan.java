package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;


@StaticFactories("Core")
public final class GreaterThan<T extends Comparable<T>> extends MatcherComposition<T>
{
    /**
     * Creates {@link Matcher} that matches if the {@link Comparable} value under test is greater than the given value.
     * <p>
     * Example
     * <pre>
     *     assertThat(1, is(greaterThan(0)));
     * </pre>
     */
    public GreaterThan(T expected)
    {
        super(new Satisfies<>(
            actual -> expected.compareTo(actual) < 0,
            new Delimited(new TextDescription("greater than"), new ValueDescription(expected))));
    }
}
