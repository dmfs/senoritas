package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.reactivestreams.Publisher;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestSubscriber;
import org.saynotobugs.senoritas.matcher.test.Test;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class PublisherThat<T> extends MatcherComposition<Function<? super TestScheduler, ? extends Publisher<T>>>
{

    @SafeVarargs
    public PublisherThat(TestEvent<T>... events)
    {
        this(new Seq<>(events));
    }


    public PublisherThat(Iterable<? extends TestEvent<T>> events)
    {
        super(new RxWithSchedulerThat<>(
            new TextDescription("Publisher that"),
            singleSource -> {
                RxTestSubscriber<T> testObserver = new RxTestSubscriber<>();
                singleSource.subscribe(testObserver);
                return testObserver;
            },
            events));
    }
}
