package org.saynotobugs.quality.quality.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Numbered;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.assessment.AllPassed;
import org.saynotobugs.quality.assessment.FailPrepended;

import static org.saynotobugs.quality.description.LiteralDescription.COMMA_NEW_LINE;


@StaticFactories("Core")
public final class Each<T> extends QualityComposition<Iterable<T>>
{

    @SafeVarargs
    public Each(Quality<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public Each(Iterable<? extends Quality<? super T>> delegates)
    {
        this(new AllOf<>(delegates));
    }


    public Each(Quality<? super T> delegate)
    {
        super(actual -> new AllPassed(new TextDescription("elements ["), COMMA_NEW_LINE, new TextDescription("]"),
                new Mapped<>(
                    numberedVerdict -> new FailPrepended(new TextDescription(numberedVerdict.left() + ": "), numberedVerdict.right()),
                    new Numbered<>(new Mapped<>(delegate::assessmentOf, actual)))),
            new Delimited(new TextDescription("each element"), delegate.description()));
    }
}
