package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.*;
import org.saynotobugs.senoritas.verdict.AnyPassed;
import org.saynotobugs.senoritas.verdict.MismatchUpdated;

import static org.saynotobugs.senoritas.description.LiteralDescription.NEW_LINE;


@StaticFactories("Core")
public final class AnyOf<T> extends MatcherComposition<T>
{
    @SafeVarargs
    public AnyOf(T... values)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(values)));
    }


    @SafeVarargs
    public AnyOf(Matcher<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public AnyOf(Iterable<? extends Matcher<? super T>> delegates)
    {
        super(actual -> new AnyPassed(new Delimited(new ValueDescription(actual), new TextDescription("neither ")),
                new Composite(new TextDescription(" nor "), NEW_LINE),
                new Mapped<>(
                    d -> new MismatchUpdated(m -> d.expectation(), d.match(actual)),
                    delegates)),
            new StructuredDescription(new TextDescription(" or "), new Mapped<>(Matcher::expectation, delegates)));
    }
}
