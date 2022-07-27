package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.jems2.Procedure;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.procedure.Composite;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxSubjectAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;

import static org.dmfs.jems2.iterable.EmptyIterable.emptyIterable;


@StaticFactories("RxJava3")
public final class Upstream<Up, Down> implements TransformerTestStep<Up, Down>
{
    private final Iterable<Procedure<RxSubjectAdapter<Up>>> mEvents;


    @SafeVarargs
    public Upstream(Procedure<RxSubjectAdapter<Up>>... events)
    {
        this(new Seq<>(events));
    }


    public Upstream(Iterable<Procedure<RxSubjectAdapter<Up>>> events)
    {
        mEvents = events;
    }


    @Override
    public Iterable<Quality<RxTestAdapter<Down>>> qualities(TestScheduler scheduler, RxSubjectAdapter<Up> upstream)
    {
        new Composite<>(mEvents).process(upstream);
        return emptyIterable();
    }
}
