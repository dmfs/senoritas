package org.saynotobugs.quality.quality.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.core.ReDescribed;


@StaticFactories("RxJava3")
public final class Immediately<T> extends RxExpectationComposition<T>
{
    public Immediately(RxExpectation<T> delegate)
    {
        super(new ActionTriggering<>(
            testScheduler -> new ReDescribed<>(orig -> new Delimited(new TextDescription("immediately"), orig), delegate.quality(testScheduler))));
    }
}
