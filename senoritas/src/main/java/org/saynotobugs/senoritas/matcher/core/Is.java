package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;


@StaticFactories("Core")
public final class Is<T> extends MatcherComposition<T>
{
    public Is(T delegate)
    {
        this(new EqualTo<>(delegate));
    }


    public Is(Matcher<T> delegate)
    {
        super(delegate);
    }
}
