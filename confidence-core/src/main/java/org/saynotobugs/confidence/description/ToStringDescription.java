package org.saynotobugs.confidence.description;

import org.saynotobugs.confidence.Description;


/**
 * A {@link Description} that describes an {@link Object} by its {@link Object#toString()} value.
 */
public final class ToStringDescription extends DescriptionComposition
{
    public ToStringDescription(Object value)
    {
        super(new QuotedDescription("<", new TextDescription(value.toString()), ">"));
    }
}
