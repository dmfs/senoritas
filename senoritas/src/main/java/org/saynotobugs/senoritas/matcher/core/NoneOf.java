
package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.verdict.AllPass;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.Pass;
import org.saynotobugs.senoritas.description.StructuredDescription;


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
        return new AllPass(
            "was",
            " and",
            "",
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
        return new StructuredDescription("none of", ",", "", new Mapped<>(Matcher::expectation, mDelegates));
    }
}
