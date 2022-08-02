package org.saynotobugs.confidence.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.ValueDescription;


@StaticFactories("Core")
public final class SameAs<T> extends QualityComposition<T>
{
    public SameAs(T expected)
    {
        super(new Satisfies<>(
            actual -> expected == actual,
            new ValueDescription(expected)));
    }
}
