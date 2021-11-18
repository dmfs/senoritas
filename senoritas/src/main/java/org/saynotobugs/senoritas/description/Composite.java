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
    private final CharSequence mDelimiter;
    private final Iterable<? extends Description> mDescriptions;


    public Composite(Description... descriptions)
    {
        this(" ", new Seq<>(descriptions));
    }


    public Composite(CharSequence delimiter, Description... descriptions)
    {
        this(delimiter, new Seq<>(descriptions));
    }


    public Composite(Iterable<? extends Description> descriptions)
    {
        this(" ", descriptions);
    }


    public Composite(CharSequence delimiter, Iterable<? extends Description> descriptions)
    {
        mDelimiter = delimiter;
        mDescriptions = descriptions;
    }


    @Override
    public void describeTo(Scribe sink)
    {
        new Delimited<Description>(() -> {},
            () -> sink.append(mDelimiter),
            () -> {},
            v -> v.describeTo(sink))
            .process(mDescriptions);
    }
}
