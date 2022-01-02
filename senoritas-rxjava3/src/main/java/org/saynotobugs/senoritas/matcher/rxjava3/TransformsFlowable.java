package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Expanded;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.AllOfFailingFast;
import org.saynotobugs.senoritas.matcher.core.ReDescribed;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.FlowableSubjectAdapter;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestSubscriber;

import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.processors.PublishProcessor;
import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class TransformsFlowable<Up, Down> implements
    Matcher<Function<? super TestScheduler, ? extends FlowableTransformer<Up, Down>>>
{
    private final Iterable<? extends TransformerEvent<Up, Down>> mEvents;


    @SafeVarargs
    public TransformsFlowable(TransformerEvent<Up, Down>... events)
    {
        this(new Seq<>(events));
    }


    public TransformsFlowable(Iterable<? extends TransformerEvent<Up, Down>> events)
    {
        mEvents = events;
    }


    @Override
    public Verdict match(Function<? super TestScheduler, ? extends FlowableTransformer<Up, Down>> actual)
    {
        TestScheduler t = new TestScheduler();
        RxTestSubscriber<Down> testAdapter = new RxTestSubscriber<>();
        PublishProcessor<Up> upstream = PublishProcessor.create();
        actual.value(t).apply(upstream.hide()).subscribe(testAdapter);
        return new AllOfFailingFast<>(
            new Expanded<>(e -> e.matchers(t, new FlowableSubjectAdapter<>(upstream)), mEvents)
        ).match(testAdapter);
    }


    @Override
    public Description expectation()
    {
        TestScheduler t = new TestScheduler();
        PublishProcessor<Up> upstream = PublishProcessor.create();
        return new ReDescribed<>(orig -> new Delimited(new TextDescription("FlowableTransformer that"), orig), new AllOfFailingFast<>(
            new Expanded<>(e -> e.matchers(t, new FlowableSubjectAdapter<>(upstream)), mEvents)
        )).expectation();
    }
}
