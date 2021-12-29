package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.function.DelegatingFunction;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.core.AllOf;
import org.saynotobugs.senoritas.matcher.core.Iterates;
import org.saynotobugs.senoritas.matcher.rxjava3.internal.Emits;
import org.saynotobugs.senoritas.matcher.rxjava3.internal.EmitsNothing;
import org.saynotobugs.senoritas.matcher.rxjava3.internal.IsComplete;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class CompletesWith<T> extends DelegatingFunction<TestScheduler, Matcher<RxTestAdapter<? extends T>>>
{
    @SafeVarargs
    public CompletesWith(T... values)
    {
        this(values.length, new Iterates<>(values));
    }


    @SafeVarargs
    public CompletesWith(Matcher<? super T>... values)
    {
        this(values.length, new Iterates<>(values));
    }


    public CompletesWith(int elementCount, Matcher<? super Iterable<? extends T>> values)
    {
        super(testScheduler -> new AllOf<>(
            new IsComplete(),
            new Emits<>(elementCount, values),
            new EmitsNothing()
        ));
    }
}
