package org.saynotobugs.quality.quality.rxjava3.internal;

import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;
import org.saynotobugs.quality.quality.core.QualityComposition;
import org.saynotobugs.quality.quality.core.Satisfies;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;


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