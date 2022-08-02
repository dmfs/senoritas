package org.saynotobugs.confidence.description;

import org.saynotobugs.confidence.Description;

import java.util.Map;


/**
 * A {@link Description} of a {@link Map}.
 */
public final class MapDescription extends DescriptionComposition
{
    public MapDescription(Map<?, ?> value)
    {
        super(new SetDescription(value.entrySet()));
    }
}
