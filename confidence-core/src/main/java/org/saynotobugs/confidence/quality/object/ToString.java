package org.saynotobugs.confidence.quality.object;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.Having;
import org.saynotobugs.confidence.quality.composite.QualityComposition;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class ToString extends QualityComposition<Object>
{
    public ToString(String toStringValue)
    {
        this(new EqualTo<>(toStringValue));
    }


    public ToString(Quality<? super String> delegate)
    {
        super(new Having<>(new TextDescription("toString()"), new TextDescription("toString()"), Object::toString, delegate));
    }
}
