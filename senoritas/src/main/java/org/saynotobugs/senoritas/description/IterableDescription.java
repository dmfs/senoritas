package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.iterable.Mapped;
import org.saynotobugs.senoritas.Description;

import static org.saynotobugs.senoritas.description.LiteralDescription.*;


/**
 * A {@link Description} of an {@link Iterable} value.
 */
public final class IterableDescription extends DelegatingDescription
{
    public IterableDescription(Iterable<?> value)
    {
        super(
            new StructuredDescription(
                new TextDescription("[ "),
                COMMA_NEW_LINE,
                new TextDescription(" ]"),
                new Mapped<>(ValueDescription::new, value)));
    }
}
