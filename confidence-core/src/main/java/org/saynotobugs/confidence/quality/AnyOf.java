package org.saynotobugs.confidence.quality;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.AnyPassed;
import org.saynotobugs.confidence.assessment.FailUpdated;
import org.saynotobugs.confidence.description.*;

import static org.saynotobugs.confidence.description.LiteralDescription.NEW_LINE;


@StaticFactories("Core")
public final class AnyOf<T> extends QualityComposition<T>
{
    @SafeVarargs
    public AnyOf(T... values)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(values)));
    }


    @SafeVarargs
    public AnyOf(Quality<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public AnyOf(Iterable<? extends Quality<? super T>> delegates)
    {
        super(actual -> new AnyPassed(new Delimited(new ValueDescription(actual), new TextDescription("neither ")),
                new Composite(new TextDescription(" nor "), NEW_LINE),
                new Mapped<>(
                    d -> new FailUpdated(m -> d.description(), d.assessmentOf(actual)),
                    delegates)),
            new StructuredDescription(new TextDescription(" or "), new Mapped<>(Quality::description, delegates)));
    }
}
