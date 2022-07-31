package org.saynotobugs.quality.quality.rxjava3.internal;

import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.QualityComposition;
import org.saynotobugs.quality.quality.Satisfies;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;


public final class IsCancelled<T> extends QualityComposition<RxTestAdapter<T>>
{
    public IsCancelled()
    {
        super(new Satisfies<>(RxTestAdapter::isCancelled, actual -> new TextDescription("was not cancelled"), new TextDescription("is cancelled")));
    }
}
