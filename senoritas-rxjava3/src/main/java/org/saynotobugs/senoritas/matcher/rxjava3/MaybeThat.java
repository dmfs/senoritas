package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestObserver;

import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class MaybeThat<T> extends MatcherComposition<Function<? super TestScheduler, ? extends MaybeSource<T>>>
{
    @SafeVarargs
    public MaybeThat(Function<? super TestScheduler, ? extends Matcher<? super RxTestAdapter<? extends T>>>... events)
    {
        this(new Seq<>(events));
    }


    public MaybeThat(Iterable<? extends Function<? super TestScheduler, ? extends Matcher<? super RxTestAdapter<? extends T>>>> events)
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
