package org.saynotobugs.quality.quality.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Numbered;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.predicate.Not;
import org.dmfs.jems2.single.Backed;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.description.Composite;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.StructuredDescription;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.assessment.FailPrepended;
import org.saynotobugs.quality.assessment.Pass;

import static org.saynotobugs.quality.description.LiteralDescription.NEW_LINE;


@StaticFactories("Core")
public final class AllOfFailingFast<T> extends QualityComposition<T>
{
    /**
     * Creates a Matcher that passes if all the given {@link Quality}s match or if no {@link Quality} was given.
     * In contrast to {@link AllOf} this Matcher stops evaluating matchers when the first {@link Quality} mismatches.
     */
    @SafeVarargs
    public AllOfFailingFast(Quality<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    /**
     * Matches if all the given {@link Quality}s match or if no {@link Quality} was given.
     * In contrast to {@link AllOf} this Matcher stops evaluating matchers when the first {@link Quality} mismatches.
     */
    public AllOfFailingFast(Iterable<? extends Quality<? super T>> delegates)
    {
        super(actual ->
                new Backed<>(
                    new First<>(new Not<>(Assessment::isSuccess),
                        new Mapped<>(
                            d -> new FailPrepended(new TextDescription("(" + d.left() + ")"), d.right().assessmentOf(actual)),
                            new Numbered<>(delegates))),
                    new Pass()).value(),
            new StructuredDescription(
                new Composite(NEW_LINE, new TextDescription("and"), NEW_LINE),
                new org.saynotobugs.quality.description.iterable.Numbered((i, d) -> new Delimited(new TextDescription("(" + i + ")"), d),
                    new Mapped<>(Quality::description, delegates))));
    }
}
