package org.saynotobugs.quality.quality.rxjava3.internal;

import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.HasSize;
import org.saynotobugs.quality.quality.Having;
import org.saynotobugs.quality.quality.QualityComposition;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;


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
