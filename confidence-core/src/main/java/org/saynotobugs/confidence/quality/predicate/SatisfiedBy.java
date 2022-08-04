package org.saynotobugs.confidence.quality.predicate;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.assessment.PassIf;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;

import java.util.function.Predicate;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class SatisfiedBy<T> extends QualityComposition<Predicate<T>>
{
    public SatisfiedBy(T testee)
    {
        super(actual -> new PassIf(actual.test(testee), new Delimited(new TextDescription("not satisfied by"), new ValueDescription(testee))),
            new Delimited(new TextDescription("satisfied by"), new ValueDescription(testee)));
    }
}
