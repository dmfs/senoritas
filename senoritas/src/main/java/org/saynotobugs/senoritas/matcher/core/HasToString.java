package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;


public final class HasToString extends DelegatingMatcher<Object>
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
