package org.saynotobugs.confidence.description;

import org.dmfs.jems2.Single;
import org.dmfs.jems2.iterable.Mapped;
import org.saynotobugs.confidence.Description;

import java.util.Set;

import static org.saynotobugs.confidence.description.LiteralDescription.COMMA_NEW_LINE;


/**
 * A {@link Description} of a {@link Set}.
 */
public final class SetDescription extends DescriptionComposition
{

    private static final TextDescription ENTRY_SEQUENCE = new TextDescription("{ ");
    private static final TextDescription EXIT_SEQUENCE = new TextDescription(" }");


    public SetDescription(Set<?> value)
    {
        this(() -> value);
    }


    public SetDescription(Single<Set<?>> value)
    {
        super(new StructuredDescription(
            ENTRY_SEQUENCE,
            COMMA_NEW_LINE,
            EXIT_SEQUENCE,
            new Mapped<>(ValueDescription::new, value.value())));
    }
}
