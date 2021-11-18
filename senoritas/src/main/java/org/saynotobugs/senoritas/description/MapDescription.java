package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.iterable.Mapped;

import java.util.Map;


public final class MapDescription extends DelegatingDescription
{
    public MapDescription(Map<?, ?> value)
    {
        super(new StructuredDescription("{", ",", "}", new Mapped<>(MapEntryDescription::new, value.entrySet())));
    }
}
