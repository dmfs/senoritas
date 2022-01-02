package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestAdapter;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.SubjectAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class Downstream<Up, Down> implements TransformerEvent<Up, Down>
{
    private final Iterable<? extends TestEvent<Down>> mEvents;


    @SafeVarargs
    public Downstream(TestEvent<Down>... events)
    {
        this(new Seq<>(events));
    }


    public Downstream(Iterable<? extends TestEvent<Down>> events)
    {
        mEvents = events;
    }


    @Override
    public Iterable<Matcher<RxTestAdapter<Down>>> matchers(TestScheduler scheduler, SubjectAdapter<Up> upstream)
    {
        return new Mapped<>(downstreamTestEvent -> downstreamTestEvent.matcher(scheduler), mEvents);
    }
}
