package org.saynotobugs.confidence.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.ReDescribed;


@StaticFactories("RxJava3")
public final class Immediately<T> extends RxExpectationComposition<T>
{
    public Immediately(RxExpectation<T> delegate)
    {
        super(new ActionTriggering<>(
            testScheduler -> new ReDescribed<>(orig -> new Delimited(new TextDescription("immediately"), orig), delegate.quality(testScheduler))));
    }
}
