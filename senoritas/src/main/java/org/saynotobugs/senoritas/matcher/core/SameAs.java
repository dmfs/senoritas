package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.PassIf;


@StaticFactories("Core")
public final class SameAs<T> extends DelegatingMatcher<T>
{
    public SameAs(T expected)
    {
        super(actual -> new PassIf(expected == actual, new ValueDescription(actual)),
            new ValueDescription(expected));
    }
}
