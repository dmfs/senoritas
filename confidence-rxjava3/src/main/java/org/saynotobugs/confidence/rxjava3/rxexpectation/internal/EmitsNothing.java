package org.saynotobugs.confidence.rxjava3.rxexpectation.internal;

import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.QualityComposition;
import org.saynotobugs.confidence.quality.Satisfies;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;


public final class EmitsNothing<T> extends QualityComposition<RxTestAdapter<T>>
{
    public EmitsNothing()
    {
        super(new Satisfies<>(actual -> actual.newValues(Integer.MAX_VALUE).size() == 0,
            actual -> new Delimited(
                new TextDescription("emitted"),
                new ValueDescription(actual.newValues(Integer.MAX_VALUE))),
            new TextDescription("emits nothing")));
    }
}