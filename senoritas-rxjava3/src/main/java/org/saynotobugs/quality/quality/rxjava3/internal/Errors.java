package org.saynotobugs.quality.quality.rxjava3.internal;

import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.quality.Having;
import org.saynotobugs.quality.quality.InstanceOf;
import org.saynotobugs.quality.quality.Iterates;
import org.saynotobugs.quality.quality.QualityComposition;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;


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
