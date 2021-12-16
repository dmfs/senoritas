package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;

import java.util.Map;


/**
 * A {@link Description} of a {@link Map.Entry}.
 */
public final class MapEntryDescription extends DescriptionComposition
{
    private static final Description SEPARATOR = new TextDescription(": ");


    public MapEntryDescription(Map.Entry<?, ?> value)
    {
        super(new Composite(
            new ValueDescription(value.getKey()),
            SEPARATOR,
            new ValueDescription(value.getValue())));
    }
}
