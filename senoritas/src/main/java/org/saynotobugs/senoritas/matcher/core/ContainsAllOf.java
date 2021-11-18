package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Matcher;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;


public final class ContainsAllOf<T> extends DelegatingMatcher<Iterable<T>>
{
    @SafeVarargs
    public ContainsAllOf(T... delegate)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(delegate)));
    }


    @SafeVarargs
    public ContainsAllOf(Matcher<? super T>... delegate)
    {
        this(new Seq<>(delegate));
    }


    public ContainsAllOf(Iterable<? extends Matcher<? super T>> delegate)
    {
        super(new AllOf<>(new Mapped<>(Contains::new, delegate)));
    }
}
