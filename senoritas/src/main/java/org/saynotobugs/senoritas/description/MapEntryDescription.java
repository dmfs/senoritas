package org.saynotobugs.senoritas.description;

import java.util.Map;


public final class MapEntryDescription extends DelegatingDescription
{
    public MapEntryDescription(Map.Entry<?, ?> value)
    {
        super(new Composite(
            new ValueDescription<>(value.getKey()),
            new TextDescription(": "),
            new ValueDescription<>(value.getValue())));
    }
}
