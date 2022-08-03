package org.saynotobugs.confidence.rxjava3.rxexpectation;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.DescriptionUpdated;
import org.saynotobugs.confidence.rxjava3.RxExpectation;
import org.saynotobugs.confidence.rxjava3.RxExpectationComposition;


@StaticFactories(value = "RxJava3", packageName = "org.saynotobugs.confidence.rxjava3")
public final class Immediately<T> extends RxExpectationComposition<T>
{
    public Immediately(RxExpectation<T> delegate)
    {
        super(new ActionTriggering<>(
            testScheduler -> new DescriptionUpdated<>(orig -> new Delimited(new TextDescription("immediately"), orig), delegate.quality(testScheduler))));
    }
}
