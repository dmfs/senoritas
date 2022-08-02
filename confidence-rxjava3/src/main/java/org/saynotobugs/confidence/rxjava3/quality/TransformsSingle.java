package org.saynotobugs.confidence.rxjava3.quality;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Expanded;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.AllOfFailingFast;
import org.saynotobugs.confidence.quality.ReDescribed;
import org.saynotobugs.confidence.rxjava3.TransformerTestStep;
import org.saynotobugs.confidence.rxjava3.adapters.RxTestObserver;
import org.saynotobugs.confidence.rxjava3.adapters.SingleSubjectAdapter;

import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import io.reactivex.rxjava3.subjects.SingleSubject;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class TransformsSingle<Up, Down> implements
    Quality<Function<? super TestScheduler, ? extends SingleTransformer<Up, Down>>>
{
    private final Iterable<? extends TransformerTestStep<Up, Down>> mEvents;


    @SafeVarargs
    public TransformsSingle(TransformerTestStep<Up, Down>... events)
    {
        this(new Seq<>(events));
    }


    public TransformsSingle(Iterable<? extends TransformerTestStep<Up, Down>> events)
    {
        mEvents = events;
    }


    @Override
    public Assessment assessmentOf(Function<? super TestScheduler, ? extends SingleTransformer<Up, Down>> candidate)
    {
        TestScheduler t = new TestScheduler();
        RxTestObserver<Down> testAdapter = new RxTestObserver<>();
        SingleSubject<Up> upstream = SingleSubject.create();
        candidate.value(t).apply(upstream.hide()).subscribe(testAdapter);
        return new AllOfFailingFast<>(
            new Expanded<>(e -> e.qualities(t, new SingleSubjectAdapter<>(upstream)), mEvents)
        ).assessmentOf(testAdapter);
    }


    @Override
    public Description description()
    {
        TestScheduler t = new TestScheduler();
        SingleSubject<Up> upstream = SingleSubject.create();
        return new ReDescribed<>(orig -> new Delimited(new TextDescription("SingleTransformer that"), orig), new AllOfFailingFast<>(
            new Expanded<>(e -> e.qualities(t, new SingleSubjectAdapter<>(upstream)), mEvents)
        )).description();
    }
}
