package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Updated;

import java.util.function.Supplier;


public final class Supplies<T> extends DelegatingMatcher<Supplier<T>>
{
    public Supplies(Matcher<? super T> delegate)
    {
        super(actual -> new Updated(description -> new Composite(new TextDescription("supplied value that"), description), delegate.match(actual.get())),
            new Composite(new TextDescription("supplies value that"), delegate.expectation()));
    }
}
