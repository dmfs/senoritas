package org.saynotobugs.confidence.quality.iterable;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Numbered;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.AllPassed;
import org.saynotobugs.confidence.assessment.FailPrepended;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.composite.AllOf;

import static org.saynotobugs.confidence.description.LiteralDescription.COMMA_NEW_LINE;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
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
