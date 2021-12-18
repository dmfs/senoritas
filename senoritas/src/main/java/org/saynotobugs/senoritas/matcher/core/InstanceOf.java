package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.PassIf;


@StaticFactories("Core")
public final class InstanceOf<T> extends MatcherComposition<T>
{
    /**
     * Creates a {@link Matcher} that matches whether the object under test is an instance of the given class.
     */
    public InstanceOf(Class<? extends T> expectation)
    {
        super(actual -> new PassIf(
                expectation.isInstance(actual),
                new Delimited(new TextDescription("instance of"), new ValueDescription(actual.getClass()))),
            new Delimited(new TextDescription("instance of"), new ValueDescription(expectation)));
    }
}
