package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.NumberDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.EqualTo;
import org.saynotobugs.senoritas.matcher.core.Iterates;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;

import java.util.Collection;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class Emits<T> implements Matcher<RxTestAdapter<T>>, Function<TestScheduler, Matcher<RxTestAdapter<T>>>
{

    private final int mEmissionCount;
    private final Matcher<? super Iterable<T>> mEmissionMatchers;


    @SafeVarargs
    public Emits(T... emissionMatchers)
    {
        this(emissionMatchers.length, new Mapped<>(EqualTo::new, new Seq<>(emissionMatchers)));
    }


    @SafeVarargs
    public Emits(Matcher<? super T>... emissionMatchers)
    {
        this(emissionMatchers.length, new Seq<>(emissionMatchers));
    }


    public Emits(int emissionCount, Iterable<? extends Matcher<? super T>> emissionMatchers)
    {
        this(emissionCount, new Iterates<>(emissionMatchers));
    }


    public Emits(int emissionCount, Matcher<? super Iterable<T>> emissionMatchers)
    {
        mEmissionCount = emissionCount;
        mEmissionMatchers = emissionMatchers;
    }


    @Override
    public Verdict match(RxTestAdapter<T> actual)
    {
        actual.awaitNext(mEmissionCount);
        Collection<T> values = actual.newValues(mEmissionCount);
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


    @Override
    public Matcher<RxTestAdapter<T>> value(TestScheduler testScheduler)
    {
        return this;
    }
}
