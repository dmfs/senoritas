package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Numbered;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.AllPassed;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;

import static org.saynotobugs.senoritas.description.LiteralDescription.COMMA_NEW_LINE;


@StaticFactories("Core")
public final class Each<T> extends MatcherComposition<Iterable<T>>
{

    @SafeVarargs
    public Each(Matcher<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public Each(Iterable<? extends Matcher<? super T>> delegates)
    {
        this(new AllOf<>(delegates));
    }


    public Each(Matcher<? super T> delegate)
    {
        super(actual -> new AllPassed(new TextDescription("elements ["), COMMA_NEW_LINE, new TextDescription("]"),
                new Mapped<>(
                    numberedVerdict -> new MismatchPrepended(new TextDescription(numberedVerdict.left() + ": "), numberedVerdict.right()),
                    new Numbered<>(new Mapped<>(delegate::match, actual)))),
            new Delimited(new TextDescription("each element"), delegate.expectation()));
    }
}
