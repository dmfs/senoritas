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
public final class Immediately<T> extends TestEventComposition<T>
{
    public Immediately(TestEvent<T> delegate)
    {
        super(new ActionTriggering<>(
            testScheduler -> new ReDescribed<>(orig -> new Delimited(new TextDescription("immediately"), orig), delegate.matcher(testScheduler))));
    }
}
