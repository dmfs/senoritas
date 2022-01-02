package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.HasSize;
import org.saynotobugs.senoritas.matcher.core.Having;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestAdapter;


@StaticFactories("RxJava3")
public final class HasNoFurtherValues<T> extends MatcherComposition<RxTestAdapter<? extends T>>
{
    public HasNoFurtherValues()
    {
        super(
            new Having<>(
                new TextDescription("pending emissions"),
                new TextDescription("pending emissions"),
                ackSubscriber -> ackSubscriber.newValues(Integer.MAX_VALUE),
                new HasSize(0)));
    }
}
