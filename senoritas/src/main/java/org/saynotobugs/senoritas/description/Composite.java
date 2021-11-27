package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.iterable.Seq;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;
import org.saynotobugs.senoritas.utils.Delimited;


/**
 * A {@link Description} composed of other {@link Description}s.
 */
public final class Composite implements Description
{
    private static final String DEFAULT_DELIMITER = " ";
    private final CharSequence mDelimiter;
    private final Iterable<? extends Description> mDescriptions;


    /**
     * Creates a {@link Description} of the sequence of the given {@link Description}s separated by {@value #DEFAULT_DELIMITER}.
     */
    public Composite(Description... descriptions)
    {
        this(DEFAULT_DELIMITER, new Seq<>(descriptions));
    }


    /**
     * Creates a {@link Description} of the sequence of the given {@link Description}s separated by the given {@code delimiter}.
     */
    public Composite(CharSequence delimiter, Description... descriptions)
    {
        this(delimiter, new Seq<>(descriptions));
    }


    public Composite(Iterable<? extends Description> descriptions)
    {
        this(DEFAULT_DELIMITER, descriptions);
    }


    public Composite(CharSequence delimiter, Iterable<? extends Description> descriptions)
    {
        mDelimiter = delimiter;
        mDescriptions = descriptions;
    }


    @Override
    public void describeTo(Scribe scribe)
    {
        new Delimited<Description>(() -> {},
            () -> scribe.append(mDelimiter),
            () -> {},
            v -> v.describeTo(scribe))
            .process(mDescriptions);
    }
}
