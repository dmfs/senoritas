package org.saynotobugs.quality.quality;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Composite;
import org.saynotobugs.quality.description.StructuredDescription;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.assessment.AllPassed;

import static org.saynotobugs.quality.description.LiteralDescription.NEW_LINE;


@StaticFactories("Core")
public final class AllOf<T> extends QualityComposition<T>
{
    /**
     * Creates a Matcher that passes if all the given {@link Quality}s match or if no {@link Quality} was given.
     */
    @SafeVarargs
    public AllOf(Quality<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    /**
     * Matches if all the given {@link Quality}s match or if no {@link Quality} was given.
     */
    public AllOf(Iterable<? extends Quality<? super T>> delegates)
    {
        super(actual -> new AllPassed(
                new TextDescription("{ "),
                new Composite(NEW_LINE, new TextDescription("and"), NEW_LINE),
                new TextDescription(" }"),
                new Mapped<>(d -> d.assessmentOf(actual), delegates)),
            new StructuredDescription(
                new Composite(NEW_LINE, new TextDescription("and"), NEW_LINE),
                new Mapped<>(Quality::description, delegates)));
    }
}
