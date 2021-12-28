package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.reactivestreams.Publisher;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.core.Having;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;


@StaticFactories("RxJava3")
public final class PublisherThat<T> extends MatcherComposition<Publisher<T>>
{
    @SafeVarargs
    public PublisherThat(Matcher<? super RxTestAdapter<T>>... events)
    {
        this(new Seq<>(events));
    }


    public PublisherThat(Iterable<? extends Matcher<? super RxTestAdapter<T>>> events)
    {
        super(new Having<>(
            matcher -> ignoredScheduler -> matcher,
            new PublisherWithSchedulerThat<T>(new Mapped<>(event -> ignoredScheduler -> event, events))));
    }
}
