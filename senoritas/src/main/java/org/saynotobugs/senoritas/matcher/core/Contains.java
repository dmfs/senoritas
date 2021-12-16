package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.single.Backed;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.Indented;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Fail;


@StaticFactories("Core")
public final class Contains<T> extends DelegatingMatcher<Iterable<T>>
{
    public Contains(T value)
    {
        this(new EqualTo<>(value));
    }


    public Contains(Matcher<? super T> delegate)
    {
        super(actual -> new Backed<>(
                new First<>(Verdict::isSuccess, new Mapped<>(delegate::match, actual)),
                new Fail(new Delimited(new TextDescription("did not contain"), new Indented(delegate.expectation()))))
                .value(),
            new Delimited(new TextDescription("contains"), new Indented(delegate.expectation())));
    }


    @SafeVarargs
    public Contains(T... values)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(values)));
    }


    @SafeVarargs
    public Contains(Matcher<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public Contains(Iterable<? extends Matcher<? super T>> delegates)
    {
        super(new AllOf<>(new Mapped<>(Contains::new, delegates)));
    }

}
