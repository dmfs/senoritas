package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.QualityComposition;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestObserver;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class ObservableThat<T> extends QualityComposition<Function<? super TestScheduler, ? extends ObservableSource<T>>>
{
    @SafeVarargs
    public ObservableThat(RxExpectation<T>... events)
    {
        this(new Seq<>(events));
    }


    public ObservableThat(Iterable<? extends RxExpectation<T>> events)
    {
        super(new RxWithSchedulerThat<>(
            new TextDescription("Observable that"),
            observableSource -> {
                RxTestObserver<T> testObserver = new RxTestObserver<>();
                observableSource.subscribe(testObserver);
                return testObserver;
            },
            events));
    }
}
