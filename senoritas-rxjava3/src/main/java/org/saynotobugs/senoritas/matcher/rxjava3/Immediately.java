package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.function.DelegatingFunction;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.ReDescribed;

import io.reactivex.rxjava3.schedulers.TestScheduler;


@StaticFactories("RxJava3")
public final class Immediately<T> extends DelegatingFunction<TestScheduler, Matcher<T>>
{
    public Immediately(Function<? super TestScheduler, ? extends Matcher<? super T>> delegate)
    {
        super(new ActionTriggering<>(
            testScheduler -> new ReDescribed<>(orig -> new Delimited(new TextDescription("immediately"), orig), delegate.value(testScheduler))));
    }
}
