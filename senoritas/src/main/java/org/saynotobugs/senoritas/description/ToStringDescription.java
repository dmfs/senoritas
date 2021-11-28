package org.saynotobugs.senoritas.description;

public final class ToStringDescription extends DelegatingDescription
{
    public ToStringDescription(Object value)
    {
        super(new QuotedDescription("<", scribe -> scribe.append(value.toString()), ">"));
    }
}
