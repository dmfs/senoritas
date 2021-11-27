package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;


public final class ObjectDescription implements Description
{
    private final Object value;


    public ObjectDescription(Object value)
    {
        this.value = value;
    }


    @Override
    public void describeTo(Scribe scribe)
    {
        scribe.append("<")
            .append(value.toString())
            .append(">");
    }
}
