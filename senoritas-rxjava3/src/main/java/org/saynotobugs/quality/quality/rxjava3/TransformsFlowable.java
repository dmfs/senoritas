package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Expanded;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.core.AllOfFailingFast;
import org.saynotobugs.quality.quality.core.ReDescribed;
import org.saynotobugs.quality.quality.rxjava3.adapters.PublishProcessorAdapter;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestSubscriber;

import io.reactivex.rxjava3.core.FlowableTransformer;
import io.reactivex.rxjava3.processors.PublishProcessor;
import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class TransformsFlowable<Up, Down> implements Quality<Function<? super TestScheduler, ? extends FlowableTransformer<Up, Down>>>
{
    private final Iterable<? extends TransformerTestStep<Up, Down>> mEvents;


    @SafeVarargs
    public TransformsFlowable(TransformerTestStep<Up, Down>... events)
    {
        this(new Seq<>(events));
    }


    public TransformsFlowable(Iterable<? extends TransformerTestStep<Up, Down>> events)
    {
        mEvents = events;
    }


    @Override
    public Assessment assessmentOf(Function<? super TestScheduler, ? extends FlowableTransformer<Up, Down>> candidate)
    {
        TestScheduler t = new TestScheduler();
        RxTestSubscriber<Down> testAdapter = new RxTestSubscriber<>();
        PublishProcessor<Up> upstream = PublishProcessor.create();
        candidate.value(t).apply(upstream.hide()).subscribe(testAdapter);
        return new AllOfFailingFast<>(
            new Expanded<>(e -> e.qualities(t, new PublishProcessorAdapter<>(upstream)), mEvents)
        ).assessmentOf(testAdapter);
    }


    @Override
    public Description description()
    {
        TestScheduler t = new TestScheduler();
        PublishProcessor<Up> upstream = PublishProcessor.create();
        return new ReDescribed<>(orig -> new Delimited(new TextDescription("FlowableTransformer that"), orig), new AllOfFailingFast<>(
            new Expanded<>(e -> e.qualities(t, new PublishProcessorAdapter<>(upstream)), mEvents)
        )).description();
    }
}
