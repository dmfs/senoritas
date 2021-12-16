package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;


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
