package org.saynotobugs.confidence.description.iterable;

import org.dmfs.jems2.BiFunction;
import org.dmfs.jems2.iterable.DelegatingIterable;
import org.dmfs.jems2.iterable.Mapped;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.description.Composite;
import org.saynotobugs.confidence.description.TextDescription;


public final class Numbered extends DelegatingIterable<Description>
{
    public Numbered(Iterable<Description> delegate)
    {
        this(new TextDescription(": "), delegate);
    }


    public Numbered(Description separator, Iterable<Description> delegate)
    {
        this((number, description) -> new Composite(new TextDescription(number.toString()), separator, description), delegate);
    }


    public Numbered(BiFunction<? super Integer, ? super Description, ? extends Description> numberFunction, Iterable<Description> delegate)
    {
        super(new Mapped<>(numbered -> numberFunction.value(numbered.left(), numbered.right()), new org.dmfs.jems2.iterable.Numbered<>(delegate)));
    }
}
