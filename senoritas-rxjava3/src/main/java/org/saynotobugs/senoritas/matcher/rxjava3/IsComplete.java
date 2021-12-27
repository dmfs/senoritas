package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.NumberDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.core.Satisfies;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;


@StaticFactories("RxJava3")
public final class IsComplete<T> extends MatcherComposition<RxTestAdapter<T>>
{
    public IsComplete()
    {
        super(new Satisfies<>(actual -> actual.completions() == 1,
            ackSubscriber -> new Delimited(new TextDescription("completed"), new NumberDescription(ackSubscriber.completions()), new TextDescription("times")),
            new TextDescription("completes exactly once")));
    }
}
