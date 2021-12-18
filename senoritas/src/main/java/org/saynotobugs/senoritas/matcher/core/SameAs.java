package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.description.ValueDescription;


@StaticFactories("Core")
public final class SameAs<T> extends MatcherComposition<T>
{
    public SameAs(T expected)
    {
        super(new Satisfies<>(
            actual -> expected == actual,
            new ValueDescription(expected)));
    }
}
