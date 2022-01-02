package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Procedure;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.procedure.Composite;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.core.AllOf;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestAdapter;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.SubjectAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class Upstream<Up, Down> implements TransformerEvent<Up, Down>
{
    private final Iterable<Procedure<SubjectAdapter<Up>>> mEvents;


    @SafeVarargs
    public Upstream(Procedure<SubjectAdapter<Up>>... events)
    {
        this(new Seq<>(events));
    }


    public Upstream(Iterable<Procedure<SubjectAdapter<Up>>> events)
    {
        mEvents = events;
    }


    @Override
    public Matcher<RxTestAdapter<Down>> matcher(TestScheduler scheduler, SubjectAdapter<Up> upstream)
    {
        new Composite<>(mEvents).process(upstream);
        return new AllOf<>();
    }
}
