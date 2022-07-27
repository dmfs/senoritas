package org.saynotobugs.quality.quality.rxjava3.internal;

import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.NumberDescription;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.core.QualityComposition;
import org.saynotobugs.quality.quality.core.Satisfies;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;


public final class IsComplete extends QualityComposition<RxTestAdapter<?>>
{
    public IsComplete()
    {
        super(new Satisfies<>(actual -> actual.completions() == 1,
            ackSubscriber -> new Delimited(new TextDescription("completed"), new NumberDescription(ackSubscriber.completions()), new TextDescription("times")),
            new TextDescription("completes exactly once")));
    }
}
