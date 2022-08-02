package org.saynotobugs.confidence.rxjava3.quality;

import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.AllOfFailingFast;
import org.saynotobugs.confidence.quality.Having;
import org.saynotobugs.confidence.quality.QualityComposition;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;
import org.saynotobugs.confidence.rxjava3.adapters.RxTestObserver;

import io.reactivex.rxjava3.core.CompletableSource;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class CompletableThat<T> extends QualityComposition<CompletableSource>
{
    @SafeVarargs
    public CompletableThat(Quality<? super RxTestAdapter<? extends T>>... events)
    {
        this(new Seq<>(events));
    }


    public CompletableThat(Iterable<? extends Quality<? super RxTestAdapter<? extends T>>> events)
    {
        super(new Having<>(new TextDescription("Completable that"),
            new TextDescription("Completable that"),
            actual -> {
                RxTestObserver<T> observer = new RxTestObserver<>();
                actual.subscribe(observer);
                return observer;
            },
            new AllOfFailingFast<>(events)));
    }
}
