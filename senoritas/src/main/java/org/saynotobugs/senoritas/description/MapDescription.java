package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.iterable.Mapped;
import org.saynotobugs.senoritas.Description;

import java.util.Map;

import static org.saynotobugs.senoritas.description.LiteralDescription.*;


/**
 * A {@link Description} of a {@link Map}.
 */
public final class MapDescription extends DelegatingDescription
{
    public MapDescription(Map<?, ?> value)
    {
        super(new StructuredDescription(new TextDescription("{ "),
            COMMA_NEW_LINE,
            new TextDescription(" }"),
            new Mapped<>(MapEntryDescription::new, value.entrySet())));
    }
}
