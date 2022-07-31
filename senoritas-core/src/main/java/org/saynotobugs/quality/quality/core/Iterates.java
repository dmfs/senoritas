package org.saynotobugs.quality.quality.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.OuterZipped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.Zipped;
import org.dmfs.jems2.single.Backed;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.assessment.AllPassed;
import org.saynotobugs.quality.assessment.Fail;
import org.saynotobugs.quality.assessment.iterable.Numbered;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.StructuredDescription;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;

import static org.saynotobugs.quality.description.LiteralDescription.COMMA_NEW_LINE;


@StaticFactories("Core")
public final class Iterates<T> implements Quality<Iterable<T>>
{
    private final Iterable<? extends Quality<? super T>> mDelegates;


    @SafeVarargs
    public Iterates(T... values)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(values)));
    }


    @SafeVarargs
    public Iterates(Quality<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public Iterates(Iterable<? extends Quality<? super T>> delegates)
    {
        this.mDelegates = delegates;
    }


    @Override
    public Assessment assessmentOf(Iterable<T> candidate)
    {
        return new AllPassed(new TextDescription("iterated [ "), COMMA_NEW_LINE, new TextDescription(" ]"),
            new Numbered(
                new OuterZipped<>(
                    mDelegates,
                    candidate,
                    (left, right) -> new Backed<>(new Zipped<>(left, right, Quality::assessmentOf),
                        () -> new Fail(left.isPresent()
                            ? new Delimited(new TextDescription("missing"), left.value().description())
                            : new Delimited(new TextDescription("unexpected"), new ValueDescription(right.value())))).value()
                )));
    }


    @Override
    public Description description()
    {
        return new StructuredDescription(
            new TextDescription("iterates [ "),
            COMMA_NEW_LINE,
            new TextDescription(" ]"),
            new org.saynotobugs.quality.description.iterable.Numbered(new Mapped<>(Quality::description, mDelegates)));
    }
}
