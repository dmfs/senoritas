package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.PassIf;


public final class LessThan<T extends Comparable<T>> extends DelegatingMatcher<T>
{
    public LessThan(T expected)
    {
        super(actual -> new PassIf(expected.compareTo(actual) > 0, new ValueDescription<>(actual)),
            new Composite(new TextDescription("less than"), new ValueDescription<>(expected)));
    }
}