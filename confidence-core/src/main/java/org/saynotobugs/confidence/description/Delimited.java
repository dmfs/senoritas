package org.saynotobugs.confidence.description;

import org.dmfs.jems2.iterable.Seq;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Scribe;
import org.saynotobugs.confidence.utils.Intermittent;

import static org.saynotobugs.confidence.description.LiteralDescription.SPACE;


/**
 * A {@link Description} that concatenates other {@link Description}s with a delimiting {@link Description}.
 */
public final class Delimited implements Description
{
    private final Description mDelimiter;
    private final Iterable<? extends Description> mDescriptions;


    /**
     * Creates a {@link Description} of the sequence of the given {@link Description}s separated with a {@link LiteralDescription#SPACE}.
     */
    public Delimited(Description... descriptions)
    {
        this(SPACE, new Seq<>(descriptions));
    }


    /**
     * Creates a {@link Description} of the sequence of the given {@link Description}s separated with the given {@code delimiter}.
     */
    public Delimited(CharSequence delimiter, Description... descriptions)
    {
        this(delimiter, new Seq<>(descriptions));
    }


    /**
     * Creates a {@link Description} of the given {@link Description} sequence separated with the given {@code delimiter}.
     */
    public Delimited(CharSequence delimiter, Iterable<? extends Description> descriptions)
    {
        this(new TextDescription(delimiter), descriptions);
    }


    /**
     * Creates a {@link Description} of the given {@link Description} sequence separated with the given {@code delimiter}.
     */
    public Delimited(Description delimiter, Iterable<? extends Description> descriptions)
    {
        mDelimiter = delimiter;
        mDescriptions = descriptions;
    }


    @Override
    public void describeTo(Scribe scribe)
    {
        new Intermittent<Description>(() -> {},
            () -> mDelimiter.describeTo(scribe),
            () -> {},
            v -> v.describeTo(scribe))
            .process(mDescriptions);
    }
}
