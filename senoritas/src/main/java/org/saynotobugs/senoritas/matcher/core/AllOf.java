package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.StructuredDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.AllPassed;

import static org.saynotobugs.senoritas.description.LiteralDescription.NEW_LINE;


@StaticFactories("Core")
public final class AllOf<T> extends MatcherComposition<T>
{
    /**
     * Creates a Matcher that passes if all the given {@link Matcher}s match or if no {@link Matcher} was given.
     */
    @SafeVarargs
    public AllOf(Matcher<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    /**
     * Matches if all the given {@link Matcher}s match or if no {@link Matcher} was given.
     */
    public AllOf(Iterable<? extends Matcher<? super T>> delegates)
    {
        super(actual -> new AllPassed(
                new TextDescription("{ "),
                new Composite(NEW_LINE, new TextDescription("and"), NEW_LINE),
                new TextDescription(" }"),
                new Mapped<>(d -> d.match(actual), delegates)),
            new StructuredDescription(
                new Composite(NEW_LINE, new TextDescription("and"), NEW_LINE),
                new Mapped<>(Matcher::expectation, delegates)));
    }
}
