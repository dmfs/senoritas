package org.saynotobugs.confidence.quality.object;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.Having;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.EqualTo;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class HasToString extends QualityComposition<Object>
{
    public HasToString(String toStringValue)
    {
        this(new EqualTo<>(toStringValue));
    }


    public HasToString(Quality<? super String> delegate)
    {
        super(new Having<>(new TextDescription("has toString()"), new TextDescription("had toString()"), Object::toString, delegate));
    }
}
