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
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestObserver;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.SingleSubjectAdapter;

import io.reactivex.rxjava3.core.SingleTransformer;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import io.reactivex.rxjava3.subjects.SingleSubject;


@StaticFactories("RxJava3")
public final class TransformsSingle<Up, Down> implements
    Matcher<Function<? super TestScheduler, ? extends SingleTransformer<Up, Down>>>
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
    public Verdict match(Function<? super TestScheduler, ? extends SingleTransformer<Up, Down>> actual)
    {
        TestScheduler t = new TestScheduler();
        RxTestObserver<Down> testAdapter = new RxTestObserver<>();
        SingleSubject<Up> upstream = SingleSubject.create();
        actual.value(t).apply(upstream.hide()).subscribe(testAdapter);
        return new AllOfFailingFast<>(
            new Expanded<>(e -> e.matchers(t, new SingleSubjectAdapter<>(upstream)), mEvents)
        ).match(testAdapter);
    }


    @Override
    public Description expectation()
    {
        TestScheduler t = new TestScheduler();
        SingleSubject<Up> upstream = SingleSubject.create();
        return new ReDescribed<>(orig -> new Delimited(new TextDescription("SingleTransformer that"), orig), new AllOfFailingFast<>(
            new Expanded<>(e -> e.matchers(t, new SingleSubjectAdapter<>(upstream)), mEvents)
        )).expectation();
    }
}
