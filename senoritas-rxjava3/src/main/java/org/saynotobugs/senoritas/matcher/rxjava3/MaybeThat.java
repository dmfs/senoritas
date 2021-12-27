package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.Guarded;
import org.saynotobugs.senoritas.matcher.core.Having;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.AckObserver;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;

import io.reactivex.rxjava3.core.MaybeSource;


@StaticFactories("RxJava3")
public final class MaybeThat<T> extends MatcherComposition<MaybeSource<T>>
{
    @SafeVarargs
    public MaybeThat(Matcher<? super RxTestAdapter<T>>... events)
    {
        this(new Seq<>(events));
    }


    public MaybeThat(Iterable<? extends Matcher<? super RxTestAdapter<T>>> events)
    {
        super(new Having<>(new TextDescription("Flowable that"),
            new TextDescription("Flowable that"),
            actual -> {
                AckObserver<T> observer = new AckObserver<>();
                actual.subscribe(observer);
                return observer;
            },
            new Guarded<>(events)));
    }
}
