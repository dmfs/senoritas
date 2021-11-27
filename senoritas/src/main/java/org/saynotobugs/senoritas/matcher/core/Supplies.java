package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.FailPrepended;

import java.util.function.Supplier;


public final class Supplies<T> extends DelegatingMatcher<Supplier<T>>
{
    public Supplies(T delegate)
    {
        this(new EqualTo<>(delegate));
    }


    public Supplies(Matcher<? super T> delegate)
    {
        super(actual -> new FailPrepended(new TextDescription("supplied value"), delegate.match(actual.get())),
            new Composite(new TextDescription("supplies value"), delegate.expectation()));
    }
}
