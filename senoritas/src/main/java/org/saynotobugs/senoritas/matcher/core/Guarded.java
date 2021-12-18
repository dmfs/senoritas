package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.predicate.Not;
import org.dmfs.jems2.single.Backed;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.StructuredDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Pass;

import static org.saynotobugs.senoritas.description.LiteralDescription.NEW_LINE;


@StaticFactories("Core")
public final class Guarded<T> extends MatcherComposition<T>
{
    /**
     * Creates a Matcher that passes if all the given {@link Matcher}s match or if no {@link Matcher} was given.
     * In contrast to {@link AllOf} this Matcher stops evaluating matchers when the first {@link Matcher} mismatches.
     */
    @SafeVarargs
    public Guarded(Matcher<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    /**
     * Matches if all the given {@link Matcher}s match or if no {@link Matcher} was given.
     * In contrast to {@link AllOf} this Matcher stops evaluating matchers when the first {@link Matcher} mismatches.
     */
    public Guarded(Iterable<? extends Matcher<? super T>> delegates)
    {
        super(actual ->
                new Backed<>(
                    new First<>(new Not<>(Verdict::isSuccess), new Mapped<>(d -> d.match(actual), delegates)),
                    new Pass()).value(),
            new StructuredDescription(
                new Composite(NEW_LINE, new TextDescription("and"), NEW_LINE),
                new Mapped<>(Matcher::expectation, delegates)));
    }
}
