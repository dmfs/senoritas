package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.PassIf;

import java.util.function.Predicate;


public final class SatisfiedBy<T> extends DelegatingMatcher<Predicate<T>>
{
    public SatisfiedBy(T testee)
    {
        super(actual -> new PassIf(actual.test(testee), new Composite(new TextDescription("not satisfied by"), new ValueDescription<>(testee))),
            new Composite(new TextDescription("satisfied by"), new ValueDescription<>(testee)));
    }
}
