package org.saynotobugs.quality.description;

import org.saynotobugs.quality.Description;


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
