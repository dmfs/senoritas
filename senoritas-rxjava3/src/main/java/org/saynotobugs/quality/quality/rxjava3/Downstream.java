package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxSubjectAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class Downstream<Up, Down> implements TransformerTestStep<Up, Down>
{
    private final Iterable<? extends RxExpectation<Down>> mEvents;


    @SafeVarargs
    public Downstream(RxExpectation<Down>... events)
    {
        this(new Seq<>(events));
    }


    public Downstream(Iterable<? extends RxExpectation<Down>> events)
    {
        mEvents = events;
    }


    @Override
    public Iterable<Quality<RxTestAdapter<Down>>> qualities(TestScheduler scheduler, RxSubjectAdapter<Up> upstream)
    {
        return new Mapped<>(downstreamTestEvent -> downstreamTestEvent.quality(scheduler), mEvents);
    }
}
