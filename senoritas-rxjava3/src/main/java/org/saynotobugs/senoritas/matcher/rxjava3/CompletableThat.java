package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.AllOfFailingFast;
import org.saynotobugs.senoritas.matcher.core.Having;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestObserver;

import io.reactivex.rxjava3.core.CompletableSource;


@StaticFactories("RxJava3")
public final class CompletableThat<T> extends MatcherComposition<CompletableSource>
{
    @SafeVarargs
    public CompletableThat(Matcher<? super RxTestAdapter<? extends T>>... events)
    {
        this(new Seq<>(events));
    }


    public CompletableThat(Iterable<? extends Matcher<? super RxTestAdapter<? extends T>>> events)
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
