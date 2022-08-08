package org.saynotobugs.confidence.quality.object;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class SameAs<T> extends QualityComposition<T>
{
    public SameAs(T expected)
    {
        super(new Satisfies<>(
            actual -> expected == actual,
            new Delimited(new TextDescription("same instance as"), new ValueDescription(expected))));
    }
}
