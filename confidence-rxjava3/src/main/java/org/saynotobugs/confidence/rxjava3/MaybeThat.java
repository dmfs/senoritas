package org.saynotobugs.confidence.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.QualityComposition;
import org.saynotobugs.confidence.rxjava3.adapters.RxTestObserver;

import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class MaybeThat<T> extends QualityComposition<Function<? super TestScheduler, ? extends MaybeSource<T>>>
{
    @SafeVarargs
    public MaybeThat(RxExpectation<T>... events)
    {
        this(new Seq<>(events));
    }


    public MaybeThat(Iterable<? extends RxExpectation<T>> events)
    {
        super(new RxWithSchedulerThat<>(
            new TextDescription("Maybe that"),
            maybeSource -> {
                RxTestObserver<T> testObserver = new RxTestObserver<>();
                maybeSource.subscribe(testObserver);
                return testObserver;
            },
            events));
    }
}
