package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.Zipped;
import org.dmfs.jems2.single.Backed;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.StructuredDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.utils.OuterZipped;
import org.saynotobugs.senoritas.verdict.AllPassed;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.iterable.Numbered;

import static org.saynotobugs.senoritas.description.LiteralDescription.COMMA_NEW_LINE;


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
        return new AllPassed(new TextDescription("iterated [ "), COMMA_NEW_LINE, new TextDescription(" ]"),
            new Numbered(
                new OuterZipped<>(
                    (left, right) -> new Backed<>(new Zipped<>(left, right, Matcher::match),
                        () -> new Fail(left.isPresent()
                            ? new Delimited(new TextDescription("missing"), left.value().expectation())
                            : new Delimited(new TextDescription("unexpected"), new ValueDescription(right.value())))).value(),
                    mDelegates,
                    actual)));
    }


    @Override
    public Description expectation()
    {
        return new StructuredDescription(
            new TextDescription("iterates [ "),
            COMMA_NEW_LINE,
            new TextDescription(" ]"),
            new org.saynotobugs.senoritas.description.iterable.Numbered(new Mapped<>(Matcher::expectation, mDelegates)));
    }
}
