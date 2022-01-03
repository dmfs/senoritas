package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.ReDescribed;


@StaticFactories("RxJava3")
public final class Immediately<T> extends RxExpectationComposition<T>
{
    public Immediately(RxExpectation<T> delegate)
    {
        super(new ActionTriggering<>(
            testScheduler -> new ReDescribed<>(orig -> new Delimited(new TextDescription("immediately"), orig), delegate.matcher(testScheduler))));
    }
}
