package org.saynotobugs.confidence.rxjava3.internal;

import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.NumberDescription;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.QualityComposition;
import org.saynotobugs.confidence.quality.Satisfies;
import org.saynotobugs.confidence.rxjava3.adapters.RxTestAdapter;


public final class IsComplete extends QualityComposition<RxTestAdapter<?>>
{
    public IsComplete()
    {
        super(new Satisfies<>(actual -> actual.completions() == 1,
            ackSubscriber -> new Delimited(new TextDescription("completed"), new NumberDescription(ackSubscriber.completions()), new TextDescription("times")),
            new TextDescription("completes exactly once")));
    }
}
