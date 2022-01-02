package org.saynotobugs.senoritas.matcher.rxjava3.internal;

import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.NumberDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.core.Satisfies;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestAdapter;


public final class IsComplete extends MatcherComposition<RxTestAdapter<?>>
{
    public IsComplete()
    {
        super(new Satisfies<>(actual -> actual.completions() == 1,
            ackSubscriber -> new Delimited(new TextDescription("completed"), new NumberDescription(ackSubscriber.completions()), new TextDescription("times")),
            new TextDescription("completes exactly once")));
    }
}
