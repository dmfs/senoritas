package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.StructuredDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.AnyPass;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;


public final class In<T> implements Matcher<T>
{
    private final Iterable<? extends Matcher<? super T>> mDelegates;


    @SafeVarargs
    public In(T... delegates)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(delegates)));
    }


    @SafeVarargs
    public In(Matcher<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public In(Iterable<? extends Matcher<? super T>> delegates)
    {
        this.mDelegates = delegates;
    }


    @Override
    public Verdict match(T actual)
    {
        return new AnyPass("{", ", ", "}", new Mapped<>(d -> d.match(actual), mDelegates));
    }


    @Override
    public Description expectation()
    {
        return new Composite(new TextDescription("in"), new StructuredDescription("{", ",", "}", new Mapped<>(Matcher::expectation, mDelegates)));
    }

}
