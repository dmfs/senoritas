package org.saynotobugs.confidence.description;

import org.saynotobugs.confidence.Description;

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
