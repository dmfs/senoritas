package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.iterable.Mapped;


public final class IterableDescription extends DelegatingDescription
{
    public IterableDescription(Iterable<?> value)
    {
        super(new StructuredDescription("[", ",", "]", new Mapped<>(ValueDescription::new, value)));
    }
}
