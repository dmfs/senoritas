package org.saynotobugs.confidence.rxjava3.rxexpectation.internal;

import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.NumberDescription;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.Satisfies;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;


public final class IsComplete extends QualityComposition<RxTestAdapter<?>>
{
    public IsComplete()
    {
        super(new Satisfies<>(actual -> actual.completions() == 1,
            ackSubscriber -> new Delimited(new TextDescription("completed"), new NumberDescription(ackSubscriber.completions()), new TextDescription("times")),
            new TextDescription("completes exactly once")));
    }
}
