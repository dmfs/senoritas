package org.saynotobugs.confidence.rxjava3.internal;

import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.QualityComposition;
import org.saynotobugs.confidence.quality.Satisfies;
import org.saynotobugs.confidence.rxjava3.adapters.RxTestAdapter;


public final class IsCancelled<T> extends QualityComposition<RxTestAdapter<T>>
{
    public IsCancelled()
    {
        super(new Satisfies<>(RxTestAdapter::isCancelled, actual -> new TextDescription("was not cancelled"), new TextDescription("is cancelled")));
    }
}
