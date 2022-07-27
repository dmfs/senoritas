package org.saynotobugs.quality.quality.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.TextDescription;


@StaticFactories("Core")
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
