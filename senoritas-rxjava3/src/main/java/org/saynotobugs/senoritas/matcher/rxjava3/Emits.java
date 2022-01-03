package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.core.EqualTo;
import org.saynotobugs.senoritas.matcher.core.Iterates;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestAdapter;


@StaticFactories("RxJava3")
public final class Emits<T> extends RxExpectationComposition<T>
{

    /**
     * Creates an {@link RxExpectation} that verifies whether the {@link RxTestAdapter} contains the given emissions.
     */
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
