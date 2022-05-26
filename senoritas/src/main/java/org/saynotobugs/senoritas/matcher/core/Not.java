package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.PassIf;


@StaticFactories("Core")
public final class Not<T> extends MatcherComposition<T>
{

    public Not(T delegate)
    {
        this(new EqualTo<>(delegate));
    }


    public Not(Matcher<? super T> delegate)
    {
        super(
            actual -> new PassIf(
                !delegate.match(actual).isSuccess(),
                new Delimited(new ValueDescription(actual), new TextDescription("("), delegate.expectation(), new TextDescription(")"))),
            new Delimited(new TextDescription("not ("), delegate.expectation(), new TextDescription(")")));
    }
}
