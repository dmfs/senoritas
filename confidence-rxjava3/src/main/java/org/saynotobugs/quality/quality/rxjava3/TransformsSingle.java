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
import org.saynotobugs.quality.quality.AllOfFailingFast;
import org.saynotobugs.quality.quality.ReDescribed;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestObserver;
import org.saynotobugs.quality.quality.rxjava3.adapters.SingleSubjectAdapter;

import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import io.reactivex.rxjava3.subjects.SingleSubject;


@StaticFactories("RxJava3")
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
