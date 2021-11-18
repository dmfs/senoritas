
package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.StructuredDescription;
import org.saynotobugs.senoritas.verdict.AnyPass;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;


public final class AnyOf<T> extends DelegatingMatcher<T>
{
    @SafeVarargs
    public AnyOf(T... values)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(values)));
    }


    @SafeVarargs
    public AnyOf(Matcher<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public AnyOf(Iterable<? extends Matcher<? super T>> delegates)
    {
        super(actual -> new AnyPass(" ", " or",
                new Mapped<>(
                    d -> d.match(actual),
                    delegates)),
            new StructuredDescription(" or", new Mapped<>(Matcher::expectation, delegates)));
    }
}
