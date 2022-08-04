package org.saynotobugs.confidence.quality.iterable;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.OuterZipped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.Zipped;
import org.dmfs.jems2.single.Backed;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.AllPassed;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.assessment.iterable.Numbered;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.StructuredDescription;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.object.EqualTo;

import static org.saynotobugs.confidence.description.LiteralDescription.COMMA_NEW_LINE;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
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
            new org.saynotobugs.confidence.description.iterable.Numbered(new Mapped<>(Quality::description, mDelegates)));
    }
}
