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
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.CompleteableSubjectAdapter;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestObserver;

import io.reactivex.rxjava3.core.CompletableTransformer;
import io.reactivex.rxjava3.schedulers.TestScheduler;
import io.reactivex.rxjava3.subjects.CompletableSubject;


@StaticFactories("RxJava3")
public final class TransformsCompletable<Up, Down> implements
    Matcher<Function<? super TestScheduler, ? extends CompletableTransformer>>
{
    private final Iterable<? extends TransformerEvent<Up, Down>> mEvents;


    @SafeVarargs
    public TransformsCompletable(TransformerEvent<Up, Down>... events)
    {
        this(new Seq<>(events));
    }


    public TransformsCompletable(Iterable<? extends TransformerEvent<Up, Down>> events)
    {
        mEvents = events;
    }


    @Override
    public Verdict match(Function<? super TestScheduler, ? extends CompletableTransformer> actual)
    {
        TestScheduler t = new TestScheduler();
        RxTestObserver<Down> testAdapter = new RxTestObserver<>();
        CompletableSubject upstream = CompletableSubject.create();
        actual.value(t).apply(upstream.hide()).subscribe(testAdapter);
        return new AllOfFailingFast<>(
            new Expanded<>(e -> e.matchers(t, new CompleteableSubjectAdapter<>(upstream)), mEvents)
        ).match(testAdapter);
    }


    @Override
    public Description expectation()
    {
        TestScheduler t = new TestScheduler();
        CompletableSubject upstream = CompletableSubject.create();
        return new ReDescribed<>(orig -> new Delimited(new TextDescription("CompletableTransformer that"), orig), new AllOfFailingFast<>(
            new Expanded<>(e -> e.matchers(t, new CompleteableSubjectAdapter<>(upstream)), mEvents)
        )).expectation();
    }
}
