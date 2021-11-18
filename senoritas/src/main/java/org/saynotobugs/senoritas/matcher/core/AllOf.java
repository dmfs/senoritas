package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.verdict.AllPass;
import org.saynotobugs.senoritas.description.StructuredDescription;


public final class AllOf<T> extends DelegatingMatcher<T>
{
    @SafeVarargs
    public AllOf(Matcher<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public AllOf(Iterable<? extends Matcher<? super T>> delegates)
    {
        super(actual -> new AllPass("", " and", new Mapped<>(d -> d.match(actual), delegates)),
            new StructuredDescription(" and", new Mapped<>(Matcher::expectation, delegates)));
    }
}
