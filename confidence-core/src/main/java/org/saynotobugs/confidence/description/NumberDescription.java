package org.saynotobugs.confidence.description;

import org.saynotobugs.confidence.Description;


/**
 * The {@link Description} of a {@link Number} value.
 */
public final class NumberDescription extends DescriptionComposition
{
    public NumberDescription(Number value)
    {
        super(new ToStringDescription(value));
    }
}
