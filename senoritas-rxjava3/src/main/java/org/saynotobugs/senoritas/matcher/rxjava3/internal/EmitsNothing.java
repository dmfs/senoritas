package org.saynotobugs.senoritas.matcher.rxjava3.internal;

import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.core.Satisfies;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;


public final class EmitsNothing extends MatcherComposition<RxTestAdapter<?>>
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