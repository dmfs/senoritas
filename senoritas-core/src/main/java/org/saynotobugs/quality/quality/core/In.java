package org.saynotobugs.quality.quality.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.*;
import org.saynotobugs.quality.assessment.AnyPassed;
import org.saynotobugs.quality.assessment.FailUpdated;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.saynotobugs.quality.description.LiteralDescription.COMMA_NEW_LINE;


@StaticFactories("Core")
public final class In<T> extends QualityComposition<T>
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
    public In(Quality<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public In(Iterable<? extends Quality<? super T>> delegates)
    {
        super(actual -> new AnyPassed(
                new Delimited(new ValueDescription(actual), new TextDescription("not in { ")),
                COMMA_NEW_LINE,
                new TextDescription(" }"),
                new Mapped<>(d -> new FailUpdated(m -> d.description(), d.assessmentOf(actual)), delegates)),
            new Delimited(
                new TextDescription("in"),
                new StructuredDescription(new TextDescription("{ "),
                    COMMA_NEW_LINE,
                    new TextDescription(" }"),
                    new Mapped<>(Quality::description, delegates))));
    }
}
