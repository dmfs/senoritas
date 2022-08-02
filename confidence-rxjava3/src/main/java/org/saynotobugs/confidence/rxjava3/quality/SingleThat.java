package org.saynotobugs.confidence.rxjava3.quality;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.QualityComposition;
import org.saynotobugs.confidence.rxjava3.RxExpectation;
import org.saynotobugs.confidence.rxjava3.adapters.RxTestObserver;

import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class SingleThat<T> extends QualityComposition<Function<? super TestScheduler, ? extends SingleSource<T>>>
{
    @SafeVarargs
    public SingleThat(RxExpectation<T>... events)
    {
        this(new Seq<>(events));
    }


    public SingleThat(Iterable<? extends RxExpectation<T>> events)
    {
        super(new RxWithSchedulerThat<>(
            new TextDescription("Single that"),
            singleSource -> {
                RxTestObserver<T> testObserver = new RxTestObserver<>();
                singleSource.subscribe(testObserver);
                return testObserver;
            },
            events));
    }
}
