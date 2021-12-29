package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.function.DelegatingFunction;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class IsCancelled extends DelegatingFunction<TestScheduler, Matcher<RxTestAdapter<?>>>
{
    public IsCancelled()
    {
        super(testScheduler -> new org.saynotobugs.senoritas.matcher.rxjava3.internal.IsCancelled());
    }
}
