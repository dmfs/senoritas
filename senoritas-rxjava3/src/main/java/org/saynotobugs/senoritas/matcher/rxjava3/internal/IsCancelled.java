package org.saynotobugs.senoritas.matcher.rxjava3.internal;

import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.core.Satisfies;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestAdapter;


public final class IsCancelled<T> extends MatcherComposition<RxTestAdapter<T>>
{
    public IsCancelled()
    {
        super(new Satisfies<>(RxTestAdapter::isCancelled, actual -> new TextDescription("was not cancelled"), new TextDescription("is cancelled")));
    }
}
