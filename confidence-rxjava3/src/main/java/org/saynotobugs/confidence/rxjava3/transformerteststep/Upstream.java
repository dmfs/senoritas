package org.saynotobugs.confidence.rxjava3.transformerteststep;

import org.dmfs.jems2.Procedure;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.procedure.Composite;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.rxjava3.RxSubjectAdapter;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;
import org.saynotobugs.confidence.rxjava3.TransformerTestStep;

import io.reactivex.rxjava3.schedulers.TestScheduler;

import static org.dmfs.jems2.iterable.EmptyIterable.emptyIterable;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
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
