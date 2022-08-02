package org.saynotobugs.confidence.rxjava3.rxexpectation.internal;

import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.HasSize;
import org.saynotobugs.confidence.quality.Having;
import org.saynotobugs.confidence.quality.QualityComposition;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;


public final class HasNoFurtherValues<T> extends QualityComposition<RxTestAdapter<T>>
{
    public HasNoFurtherValues()
    {
        super(
            new Having<>(
                new TextDescription("pending emissions"),
                new TextDescription("pending emissions"),
                ackSubscriber -> ackSubscriber.newValues(Integer.MAX_VALUE),
                new HasSize(0)));
    }
}
