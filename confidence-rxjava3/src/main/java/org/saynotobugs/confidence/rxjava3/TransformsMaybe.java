package org.saynotobugs.confidence.rxjava3;

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
import org.saynotobugs.confidence.rxjava3.adapters.MaybeSubjectAdapter;
import org.saynotobugs.confidence.rxjava3.adapters.RxTestObserver;

import io.reactivex.rxjava3.core.MaybeTransformer;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import io.reactivex.rxjava3.subjects.MaybeSubject;


@StaticFactories("RxJava3")
public final class TransformsMaybe<Up, Down> implements Quality<Function<? super TestScheduler, ? extends MaybeTransformer<Up, Down>>>
{
    private final Iterable<? extends TransformerTestStep<Up, Down>> mEvents;


    @SafeVarargs
    public TransformsMaybe(TransformerTestStep<Up, Down>... events)
    {
        this(new Seq<>(events));
    }


    public TransformsMaybe(Iterable<? extends TransformerTestStep<Up, Down>> events)
    {
        mEvents = events;
    }


    @Override
    public Assessment assessmentOf(Function<? super TestScheduler, ? extends MaybeTransformer<Up, Down>> candidate)
    {
        TestScheduler t = new TestScheduler();
        RxTestObserver<Down> testAdapter = new RxTestObserver<>();
        MaybeSubject<Up> upstream = MaybeSubject.create();
        candidate.value(t).apply(upstream.hide()).subscribe(testAdapter);
        return new AllOfFailingFast<>(
            new Expanded<>(e -> e.qualities(t, new MaybeSubjectAdapter<>(upstream)), mEvents)
        ).assessmentOf(testAdapter);
    }


    @Override
    public Description description()
    {
        TestScheduler t = new TestScheduler();
        MaybeSubject<Up> upstream = MaybeSubject.create();
        return new ReDescribed<>(orig -> new Delimited(new TextDescription("MaybeTransformer that"), orig), new AllOfFailingFast<>(
            new Expanded<>(e -> e.qualities(t, new MaybeSubjectAdapter<>(upstream)), mEvents)
        )).description();
    }
}
