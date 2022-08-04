package org.saynotobugs.confidence.rxjava3.rxexpectation.internal;

import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.object.InstanceOf;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.composite.Having;
import org.saynotobugs.confidence.quality.iterable.Iterates;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;


public final class Errors<T> extends QualityComposition<RxTestAdapter<T>>
{
    public Errors(Class<Throwable> error)
    {
        this(new InstanceOf<>(error));
    }


    public Errors(Quality<? super Throwable> errorQuality)
    {
        super(new Having<>("error", RxTestAdapter::errors, new Iterates<>(errorQuality)));
    }
}
