package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.StructuredDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.AllPassed;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.Pass;

import static org.saynotobugs.senoritas.description.LiteralDescription.EMPTY;
import static org.saynotobugs.senoritas.description.LiteralDescription.NEW_LINE;


@StaticFactories("Core")
public final class NoneOf<T> implements Matcher<T>
{
    private final Iterable<? extends Matcher<? super T>> mDelegates;


    @SafeVarargs
    public NoneOf(T... values)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(values)));
    }


    @SafeVarargs
    public NoneOf(Matcher<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public NoneOf(Iterable<? extends Matcher<? super T>> delegates)
    {
        mDelegates = delegates;
    }


    @Override
    public Verdict match(T actual)
    {
        return new AllPassed(
            new TextDescription("was"), new Composite(new TextDescription(" and"), NEW_LINE), EMPTY,
            new Mapped<>(
                delegate -> {
                    Verdict result = delegate.match(actual);
                    if (result.isSuccess())
                    {
                        return new Fail(delegate.expectation());
                    }
                    return new Pass();
                },
                mDelegates));
    }


    @Override
    public Description expectation()
    {
        return new StructuredDescription(new TextDescription("None of "), new Composite(NEW_LINE, new TextDescription("and"), NEW_LINE), EMPTY,
            new Mapped<>(Matcher::expectation, mDelegates));
    }
}
