package org.saynotobugs.confidence.description;

import java.util.Optional;


/**
 * The Description of an {@link Optional} value.
 */
@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public final class OptionalDescription extends DescriptionComposition
{
    public OptionalDescription(Optional<?> value)
    {
        super(
            value.isPresent()
                ? new Composite(new TextDescription("<present "), new ValueDescription(value.get()), new TextDescription(">"))
                : new TextDescription("<empty>")
        );
    }
}
