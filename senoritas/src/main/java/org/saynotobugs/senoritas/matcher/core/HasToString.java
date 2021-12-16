package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.MatcherComposition;


@StaticFactories("Core")
public final class HasToString extends MatcherComposition<Object>
{
    public HasToString(String toStringValue)
    {
        this(new EqualTo<>(toStringValue));
    }


    public HasToString(Matcher<? super String> delegate)
    {
        super(new Having<>(new TextDescription("has toString()"), new TextDescription("had toString()"), Object::toString, delegate));
    }
}
