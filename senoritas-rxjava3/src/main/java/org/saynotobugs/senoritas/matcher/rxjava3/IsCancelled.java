package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.core.Satisfies;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;


@StaticFactories("RxJava3")
public final class IsCancelled<T> extends MatcherComposition<RxTestAdapter<T>>
{
    public IsCancelled()
    {
        super(new Satisfies<>(RxTestAdapter::isCancelled, actual -> new TextDescription("was not cancelled"), new TextDescription("is cancelled")));
    }
}
