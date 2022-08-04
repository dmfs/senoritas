package org.saynotobugs.confidence.rxjava3.quality;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.reactivestreams.Publisher;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.rxjava3.RxExpectation;
import org.saynotobugs.confidence.rxjava3.adapters.RxTestSubscriber;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class PublisherThat<T> extends QualityComposition<Function<? super TestScheduler, ? extends Publisher<T>>>
{

    @SafeVarargs
    public PublisherThat(RxExpectation<T>... events)
    {
        this(new Seq<>(events));
    }


    public PublisherThat(Iterable<? extends RxExpectation<T>> events)
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
