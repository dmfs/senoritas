package org.saynotobugs.quality.quality.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.description.ValueDescription;


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
