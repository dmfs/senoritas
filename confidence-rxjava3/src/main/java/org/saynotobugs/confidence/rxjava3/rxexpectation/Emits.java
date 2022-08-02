package org.saynotobugs.confidence.rxjava3.rxexpectation;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.EqualTo;
import org.saynotobugs.confidence.quality.Iterates;
import org.saynotobugs.confidence.rxjava3.RxExpectation;
import org.saynotobugs.confidence.rxjava3.RxExpectationComposition;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
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
    public Emits(Quality<? super T>... emissionQualities)
    {
        this(emissionQualities.length, new Seq<>(emissionQualities));
    }


    public Emits(int emissionCount, Iterable<? extends Quality<? super T>> emissionsQualities)
    {
        this(emissionCount, new Iterates<>(emissionsQualities));
    }


    public Emits(int emissionCount, Quality<? super Iterable<T>> emissionsQuality)
    {
        super(testScheduler -> new org.saynotobugs.confidence.rxjava3.rxexpectation.internal.Emits<>(emissionCount, emissionsQuality));
    }
}
