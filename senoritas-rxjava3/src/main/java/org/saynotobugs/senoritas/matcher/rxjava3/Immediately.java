package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.jems2.function.DelegatingFunction;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.ReDescribed;

import io.reactivex.rxjava3.schedulers.TestScheduler;


public final class Immediately<T> extends DelegatingFunction<TestScheduler, Matcher<T>>
{
    public Immediately(Matcher<? super T> delegate)
    {
        super(new ActionTriggering<>(testScheduler -> new ReDescribed<>(orig -> new Delimited(new TextDescription("immediately"), orig), delegate)));
    }
}
