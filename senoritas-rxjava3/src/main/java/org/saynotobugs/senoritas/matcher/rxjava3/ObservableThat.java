package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.Guarded;
import org.saynotobugs.senoritas.matcher.core.Having;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestObserver;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;

import io.reactivex.rxjava3.core.ObservableSource;


@StaticFactories("RxJava3")
public final class ObservableThat<T> extends MatcherComposition<ObservableSource<T>>
{
    @SafeVarargs
    public ObservableThat(Matcher<? super RxTestAdapter<T>>... events)
    {
        this(new Seq<>(events));
    }


    public ObservableThat(Iterable<? extends Matcher<? super RxTestAdapter<T>>> events)
    {
        super(new Having<>(new TextDescription("Observable that"),
            new TextDescription("Observable that"),
            actual -> {
                RxTestObserver<T> observer = new RxTestObserver<>();
                actual.subscribe(observer);
                return observer;
            },
            new Guarded<>(events)));
    }
}
