package org.saynotobugs.senoritas.matcher.rxjava3.internal;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.NumberDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;

import java.util.Collection;


public final class Emits<T> implements Matcher<RxTestAdapter<? extends T>>
{

    private final int mEmissionCount;
    private final Matcher<? super Iterable<? extends T>> mEmissionMatchers;


    public Emits(int emissionCount, Matcher<? super Iterable<? extends T>> emissionMatchers)
    {
        mEmissionCount = emissionCount;
        mEmissionMatchers = emissionMatchers;
    }


    @Override
    public Verdict match(RxTestAdapter<? extends T> actual)
    {
        actual.awaitNext(mEmissionCount);
        Collection<? extends T> values = actual.newValues(mEmissionCount);
        Verdict result = mEmissionMatchers.match(values);
        if (result.isSuccess())
        {
            actual.ack(values.size());
        }
        return new MismatchPrepended(
            new Delimited(
                new TextDescription("emitted"),
                new NumberDescription(values.size()),
                new TextDescription("items that")),
            result);
    }


    @Override
    public Description expectation()
    {
        return new Delimited(
            new TextDescription("emits"),
            new NumberDescription(mEmissionCount),
            new TextDescription("items that"),
            mEmissionMatchers.expectation());
    }
}
