package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.procedure.ForEach;
import org.saynotobugs.senoritas.Description;


/**
 * A {@link Description} composed of other {@link Description}s.
 */
public final class Composite extends DescriptionComposition
{
    /**
     * Creates a {@link Description} of the sequence of the given {@link Description}s.
     */
    public Composite(Description... descriptions)
    {
        this(new Seq<>(descriptions));
    }


    /**
     * Creates a {@link Description} of the given {@link Description} sequence.
     */
    public Composite(Iterable<? extends Description> descriptions)
    {
        super(scribe -> new ForEach<>(descriptions).process(d -> d.describeTo(scribe)));
    }
}
