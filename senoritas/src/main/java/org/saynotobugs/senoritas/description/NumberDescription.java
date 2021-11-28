package org.saynotobugs.senoritas.description;

public final class NumberDescription extends DelegatingDescription
{
    public NumberDescription(Number value)
    {
        super(new ToStringDescription(value));
    }
}
