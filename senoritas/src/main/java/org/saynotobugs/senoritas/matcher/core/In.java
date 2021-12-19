package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.*;
import org.saynotobugs.senoritas.verdict.AnyPassed;
import org.saynotobugs.senoritas.verdict.MismatchUpdated;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.saynotobugs.senoritas.description.LiteralDescription.COMMA_NEW_LINE;


@StaticFactories("Core")
public final class In<T> extends MatcherComposition<T>
{
    @SafeVarargs
    public In(T... delegates)
    {
        this(asList(delegates));
    }


    public In(Collection<? extends T> delegates)
    {
        super(
            new Satisfies<>(delegates::contains,
                actual -> new Delimited(new ValueDescription(actual), new TextDescription("not in"), new IterableDescription(delegates)),
                new Delimited(new TextDescription("in"), new IterableDescription(delegates))));
    }


    @SafeVarargs
    public In(Matcher<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public In(Iterable<? extends Matcher<? super T>> delegates)
    {
        super(actual -> new AnyPassed(
                new Delimited(new ValueDescription(actual), new TextDescription("not in { ")),
                COMMA_NEW_LINE,
                new TextDescription(" }"),
                new Mapped<>(d -> new MismatchUpdated(m -> d.expectation(), d.match(actual)), delegates)),
            new Delimited(
                new TextDescription("in"),
                new StructuredDescription(new TextDescription("{ "),
                    COMMA_NEW_LINE,
                    new TextDescription(" }"),
                    new Mapped<>(Matcher::expectation, delegates))));
    }
}
