package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;


@StaticFactories("Core")
public final class LessThan<T extends Comparable<T>> extends MatcherComposition<T>
{

    /**
     * Creates {@link Matcher} that matches if the {@link Comparable} value under test is less than the given value.
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