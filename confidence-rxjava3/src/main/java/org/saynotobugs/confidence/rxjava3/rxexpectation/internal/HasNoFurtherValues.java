package org.saynotobugs.confidence.rxjava3.rxexpectation.internal;

import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.collection.HasSize;
import org.saynotobugs.confidence.quality.composite.Has;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;


public final class HasNoFurtherValues<T> extends QualityComposition<RxTestAdapter<T>>
{
    public HasNoFurtherValues()
    {
        super(
            new Has<>(
                new TextDescription("pending emissions"),
                new TextDescription("pending emissions"),
                ackSubscriber -> ackSubscriber.newValues(Integer.MAX_VALUE),
                new HasSize(0)));
    }
}
