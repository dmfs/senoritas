package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.PassIf;

import java.util.function.Predicate;


@StaticFactories("Core")
public final class SatisfiedBy<T> extends DelegatingMatcher<Predicate<T>>
{
    public SatisfiedBy(T testee)
    {
        super(actual -> new PassIf(actual.test(testee), new Delimited(new TextDescription("not satisfied by"), new ValueDescription(testee))),
            new Delimited(new TextDescription("satisfied by"), new ValueDescription(testee)));
    }
}
