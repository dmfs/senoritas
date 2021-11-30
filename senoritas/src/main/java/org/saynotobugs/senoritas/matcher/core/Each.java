package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Numbered;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.AllPassed;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;

import static org.saynotobugs.senoritas.description.LiteralDescription.COMMA_NEW_LINE;
import static org.saynotobugs.senoritas.description.LiteralDescription.NEW_LINE;


public final class Each<T> extends DelegatingMatcher<Iterable<T>>
{

    @SafeVarargs
    public Each(Matcher<? super T>... delegates)
    {
        this("each value", new AllOf<>(delegates));
    }


    @SafeVarargs
    public Each(String description, Matcher<? super T>... delegate)
    {
        this(description, new AllOf<>(delegate));
    }


    public Each(Matcher<? super T> delegate)
    {
        this("each value", delegate);
    }


    public Each(String description, Matcher<? super T> delegate)
    {
        super(actual -> new AllPassed(new TextDescription("values ["), COMMA_NEW_LINE, new TextDescription("]"),
                new Mapped<>(
                    numberedVerdict -> new MismatchPrepended(new TextDescription(numberedVerdict.left() + ": "), numberedVerdict.right()),
                    new Numbered<>(new Mapped<>(delegate::match, actual)))),
            new Delimited(new TextDescription(description), delegate.expectation()));
    }
}
