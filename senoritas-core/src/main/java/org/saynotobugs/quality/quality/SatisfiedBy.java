package org.saynotobugs.quality.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;
import org.saynotobugs.quality.assessment.PassIf;

import java.util.function.Predicate;


@StaticFactories("Core")
public final class SatisfiedBy<T> extends QualityComposition<Predicate<T>>
{
    public SatisfiedBy(T testee)
    {
        super(actual -> new PassIf(actual.test(testee), new Delimited(new TextDescription("not satisfied by"), new ValueDescription(testee))),
            new Delimited(new TextDescription("satisfied by"), new ValueDescription(testee)));
    }
}
