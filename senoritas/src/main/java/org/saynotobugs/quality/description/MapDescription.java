package org.saynotobugs.quality.description;

import org.dmfs.jems2.iterable.Mapped;
import org.saynotobugs.quality.Description;

import java.util.Map;

import static org.saynotobugs.quality.description.LiteralDescription.COMMA_NEW_LINE;


/**
 * A {@link Description} of a {@link Map}.
 */
public final class MapDescription extends DescriptionComposition
{

    private static final TextDescription ENTRY_SEQUENCE = new TextDescription("{ ");
    private static final TextDescription EXIT_SEQUENCE = new TextDescription(" }");


    public MapDescription(Map<?, ?> value)
    {
        super(new StructuredDescription(
            ENTRY_SEQUENCE,
            COMMA_NEW_LINE,
            EXIT_SEQUENCE,
            new Mapped<>(MapEntryDescription::new, value.entrySet())));
    }
}
