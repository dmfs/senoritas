package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;


public final class NumberDescription implements Description
{
    private final Number value;


    public NumberDescription(Number value)
    {
        this.value = value;
    }


    @Override
    public void describeTo(Scribe sink)
    {
        sink.append("<")
            .append(value.toString())
            .append(">");
    }
}
