package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.AllOfFailingFast;
import org.saynotobugs.senoritas.matcher.core.ReDescribed;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.ObservableSubjectAdapter;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestObserver;

import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import io.reactivex.rxjava3.subjects.PublishSubject;


@StaticFactories("RxJava3")
public final class TransformsObservable<Up, Down> implements
    Matcher<Function<? super TestScheduler, ? extends ObservableTransformer<Up, Down>>>
{
    private final Iterable<? extends TransformerEvent<Up, Down>> mEvents;


    @SafeVarargs
    public TransformsObservable(TransformerEvent<Up, Down>... events)
    {
        this(new Seq<>(events));
    }


    public TransformsObservable(Iterable<? extends TransformerEvent<Up, Down>> events)
    {
        mEvents = events;
    }


    @Override
    public Verdict match(Function<? super TestScheduler, ? extends ObservableTransformer<Up, Down>> actual)
    {
        TestScheduler t = new TestScheduler();
        RxTestObserver<Down> testAdapter = new RxTestObserver<>();
        PublishSubject<Up> upstream = PublishSubject.create();
        actual.value(t).apply(upstream.hide()).subscribe(testAdapter);
        return new AllOfFailingFast<>(
            new Mapped<>(e -> e.matcher(t, new ObservableSubjectAdapter<>(upstream)), mEvents)
        ).match(testAdapter);
    }


    @Override
    public Description expectation()
    {
        TestScheduler t = new TestScheduler();
        PublishSubject<Up> upstream = PublishSubject.create();
        return new ReDescribed<>(orig -> new Delimited(new TextDescription("ObservableTransformer that"), orig), new AllOfFailingFast<>(
            new Mapped<>(e -> e.matcher(t, new ObservableSubjectAdapter<>(upstream)), mEvents)
        )).expectation();
    }
}
