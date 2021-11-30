package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.single.Collected;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.StructuredDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.PassIf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.saynotobugs.senoritas.description.LiteralDescription.COMMA_NEW_LINE;


public final class IteratesInAnyOrder<T> implements Matcher<Iterable<T>>
{
    private final Iterable<? extends Matcher<? super T>> mDelegates;


    @SafeVarargs
    public IteratesInAnyOrder(T... delegate)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(delegate)));
    }


    @SafeVarargs
    public IteratesInAnyOrder(Matcher<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public IteratesInAnyOrder(Iterable<? extends Matcher<? super T>> delegates)
    {
        mDelegates = delegates;
    }


    @Override
    public Verdict match(Iterable<T> actual)
    {
        List<T> values = new Collected<>(ArrayList::new, actual).value();
        List<? extends Matcher<? super T>> delegates = new Collected<>(ArrayList::new, mDelegates).value();

        outer:
        for (Iterator<T> i = values.iterator(); i.hasNext(); )
        {
            T next = i.next();
            for (Iterator<? extends Matcher<? super T>> m = delegates.iterator(); m.hasNext(); )
            {
                if (m.next().match(next).isSuccess())
                {
                    m.remove();
                    i.remove();
                    continue outer;
                }
            }
        }

        return new PassIf(values.size() == 0 && delegates.size() == 0,
            new StructuredDescription(",", new Seq<>(
                new StructuredDescription(
                    new TextDescription("contained also ["),
                    COMMA_NEW_LINE,
                    new TextDescription("]"),
                    new Mapped<>(ValueDescription::new, values)),
                new StructuredDescription(
                    new TextDescription("did not contain ["),
                    COMMA_NEW_LINE,
                    new TextDescription("]"),
                    new Mapped<>(Matcher::expectation, delegates)))));
    }


    @Override
    public Description expectation()
    {
        return new StructuredDescription(
            new TextDescription("contains in any order ["),
            COMMA_NEW_LINE,
            new TextDescription("]"),
            new Mapped<>(Matcher::expectation, mDelegates));
    }
}
