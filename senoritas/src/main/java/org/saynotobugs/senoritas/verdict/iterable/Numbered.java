package org.saynotobugs.senoritas.verdict.iterable;

import org.dmfs.jems2.BiFunction;
import org.dmfs.jems2.iterable.DelegatingIterable;
import org.dmfs.jems2.iterable.Mapped;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.MismatchUpdated;


public final class Numbered extends DelegatingIterable<Verdict>
{
    public Numbered(Iterable<Verdict> delegate)
    {
        this(new TextDescription(": "), delegate);
    }


    public Numbered(Description separator, Iterable<Verdict> delegate)
    {
        this((number, description) -> new Composite(new TextDescription(number.toString()), separator, description), delegate);
    }


    public Numbered(BiFunction<? super Integer, ? super Description, ? extends Description> numberFunction, Iterable<Verdict> delegate)
    {
        super(new Mapped<>(
            numbered -> new MismatchUpdated(description -> numberFunction.value(numbered.left(), description), numbered.right()),
            new org.dmfs.jems2.iterable.Numbered<>(delegate)));
    }
}
