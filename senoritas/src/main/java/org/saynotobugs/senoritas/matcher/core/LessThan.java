package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.PassIf;


@StaticFactories("Core")
public final class LessThan<T extends Comparable<T>> extends DelegatingMatcher<T>
{
    public LessThan(T expected)
    {
        super(actual -> new PassIf(expected.compareTo(actual) > 0, new ValueDescription(actual)),
            new Delimited(new TextDescription("less than"), new ValueDescription(expected)));
    }
}