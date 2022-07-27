package org.saynotobugs.quality.assessment.iterable;

import org.dmfs.jems2.BiFunction;
import org.dmfs.jems2.iterable.DelegatingIterable;
import org.dmfs.jems2.iterable.Mapped;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.description.Composite;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.assessment.FailUpdated;


public final class Numbered extends DelegatingIterable<Assessment>
{
    public Numbered(Iterable<Assessment> delegate)
    {
        this(new TextDescription(": "), delegate);
    }


    public Numbered(Description separator, Iterable<Assessment> delegate)
    {
        this((number, description) -> new Composite(new TextDescription(number.toString()), separator, description), delegate);
    }


    public Numbered(BiFunction<? super Integer, ? super Description, ? extends Description> numberFunction, Iterable<Assessment> delegate)
    {
        super(new Mapped<>(
            numbered -> new FailUpdated(description -> numberFunction.value(numbered.left(), description), numbered.right()),
            new org.dmfs.jems2.iterable.Numbered<>(delegate)));
    }
}
