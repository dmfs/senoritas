package org.saynotobugs.confidence.rxjava3.rxexpectation.internal;

import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.Satisfies;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;


public final class IsCancelled<T> extends QualityComposition<RxTestAdapter<T>>
{
    public IsCancelled()
    {
        super(new Satisfies<>(RxTestAdapter::isCancelled, actual -> new TextDescription("was not cancelled"), new TextDescription("is cancelled")));
    }
}
