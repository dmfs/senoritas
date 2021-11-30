package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.PassIf;


public final class SameAs<T> extends DelegatingMatcher<T>
{
    public SameAs(T expected)
    {
        super(actual -> new PassIf(expected == actual, new ValueDescription(actual)),
            new ValueDescription(expected));
    }
}
