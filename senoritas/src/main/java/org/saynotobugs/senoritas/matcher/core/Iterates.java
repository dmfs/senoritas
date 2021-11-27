package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Numbered;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.Zipped;
import org.dmfs.jems2.single.Backed;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.StructuredDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.utils.OuterZipped;
import org.saynotobugs.senoritas.verdict.AllPass;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.FailPrepended;


public final class Iterates<T> implements Matcher<Iterable<T>>
{
    private final Iterable<? extends Matcher<? super T>> mDelegates;


    @SafeVarargs
    public Iterates(T... values)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(values)));
    }


    @SafeVarargs
    public Iterates(Matcher<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public Iterates(Iterable<? extends Matcher<? super T>> delegates)
    {
        this.mDelegates = delegates;
    }


    @Override
    public Verdict match(Iterable<T> actual)
    {
        return new AllPass("iterated [", ",", "]",
            new Mapped<>(
                n -> new FailPrepended(new TextDescription(n.left() + ":"), n.right()),
                new Numbered<>(
                    new OuterZipped<>(
                        mDelegates,
                        actual,
                        (left, right) -> new Backed<>(new Zipped<>(left, right, Matcher::match),
                            () -> new Fail(left.isPresent()
                                ? new Composite(new TextDescription("missing"), left.value().expectation())
                                : new Composite(new TextDescription("unexpected"), new ValueDescription<>(right.value())))).value()))));
    }


    @Override
    public Description expectation()
    {
        return new StructuredDescription(
            "iterates [",
            ",",
            "]",
            new Mapped<>(d -> new Composite(new TextDescription(d.left() + ":"), d.right().expectation()), new Numbered<>(mDelegates)));
    }
}
