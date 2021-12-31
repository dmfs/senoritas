package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.function.DelegatingFunction;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.core.Anything;
import org.saynotobugs.senoritas.matcher.core.Contains;
import org.saynotobugs.senoritas.matcher.core.Having;
import org.saynotobugs.senoritas.matcher.core.NoneOf;
import org.saynotobugs.senoritas.matcher.rxjava3.internal.IsCancelled;
import org.saynotobugs.senoritas.matcher.rxjava3.internal.IsComplete;
import org.saynotobugs.senoritas.matcher.rxjava3.utils.RxTestAdapter;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class IsAlive<T> extends TestEventComposition<T>
{
    public IsAlive()
    {
        super(testScheduler -> new NoneOf<>(
            new Having<>("error", RxTestAdapter::errors, new Contains<>(new Anything())),
            new IsComplete(),
            new IsCancelled<>()
        ));
    }
}
