package org.saynotobugs.quality.quality.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;


@StaticFactories("Core")
public final class Is<T> extends QualityComposition<T>
{
    public Is(T delegate)
    {
        this(new EqualTo<>(delegate));
    }


    public Is(Quality<T> delegate)
    {
        super(delegate);
    }
}
