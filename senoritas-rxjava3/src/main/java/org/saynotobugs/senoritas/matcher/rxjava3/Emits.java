package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.function.DelegatingFunction;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.core.EqualTo;
import org.saynotobugs.senoritas.matcher.core.Iterates;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class Emits<T> extends DelegatingFunction<TestScheduler, Matcher<RxTestAdapter<? extends T>>>
{

    @SafeVarargs
    public Emits(T... emissions)
    {
        this(emissions.length, new Mapped<>(EqualTo::new, new Seq<>(emissions)));
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


    public Emits(int emissionCount, Matcher<? super Iterable<? extends T>> emissionsMatcher)
    {
        super(testScheduler -> new org.saynotobugs.senoritas.matcher.rxjava3.internal.Emits<>(emissionCount, emissionsMatcher));
    }
}
