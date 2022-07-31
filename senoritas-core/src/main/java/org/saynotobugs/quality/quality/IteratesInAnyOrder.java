package org.saynotobugs.quality.quality;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.single.Collected;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.description.StructuredDescription;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;
import org.saynotobugs.quality.assessment.PassIf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.saynotobugs.quality.description.LiteralDescription.COMMA_NEW_LINE;


@StaticFactories("Core")
public final class IteratesInAnyOrder<T> implements Quality<Iterable<T>>
{
    private final Iterable<? extends Quality<? super T>> mDelegates;


    @SafeVarargs
    public IteratesInAnyOrder(T... delegate)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(delegate)));
    }


    @SafeVarargs
    public IteratesInAnyOrder(Quality<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public IteratesInAnyOrder(Iterable<? extends Quality<? super T>> delegates)
    {
        mDelegates = delegates;
    }


    @Override
    public Assessment assessmentOf(Iterable<T> candidate)
    {
        List<T> values = new Collected<>(ArrayList::new, candidate).value();
        List<? extends Quality<? super T>> delegates = new Collected<>(ArrayList::new, mDelegates).value();

        outer:
        for (Iterator<T> i = values.iterator(); i.hasNext(); )
        {
            T next = i.next();
            for (Iterator<? extends Quality<? super T>> m = delegates.iterator(); m.hasNext(); )
            {
                if (m.next().assessmentOf(next).isSuccess())
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
                    new Mapped<>(Quality::description, delegates)))));
    }


    @Override
    public Description description()
    {
        return new StructuredDescription(
            new TextDescription("contains in any order ["),
            COMMA_NEW_LINE,
            new TextDescription("]"),
            new Mapped<>(Quality::description, mDelegates));
    }
}
