package org.saynotobugs.confidence.description;

import org.dmfs.jems2.iterable.Mapped;
import org.saynotobugs.confidence.Description;

import static org.saynotobugs.confidence.description.LiteralDescription.COMMA_NEW_LINE;


/**
 * A {@link Description} of an {@link Iterable} value.
 */
public final class IterableDescription extends DescriptionComposition
{

    private static final TextDescription ENTRY_SEQUENCE = new TextDescription("[ ");
    private static final TextDescription EXIT_SEQUENCE = new TextDescription(" ]");


    public IterableDescription(Iterable<?> value)
    {
        super(new StructuredDescription(
            ENTRY_SEQUENCE,
            COMMA_NEW_LINE,
            EXIT_SEQUENCE,
            new Mapped<>(ValueDescription::new, value)));
    }
}
