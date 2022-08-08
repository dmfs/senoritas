package org.saynotobugs.confidence.quality.object;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.composite.Has;
import org.saynotobugs.confidence.quality.composite.QualityComposition;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class HasToString extends QualityComposition<Object>
{
    public HasToString(String toStringValue)
    {
        this(new EqualTo<>(toStringValue));
    }


    public HasToString(Quality<? super String> delegate)
    {
        super(new Has<>("toString()", Object::toString, delegate));
    }
}
