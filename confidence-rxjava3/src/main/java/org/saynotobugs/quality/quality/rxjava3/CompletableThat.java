package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.AllOfFailingFast;
import org.saynotobugs.quality.quality.Having;
import org.saynotobugs.quality.quality.QualityComposition;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestObserver;

import io.reactivex.rxjava3.core.CompletableSource;


@StaticFactories("RxJava3")
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
