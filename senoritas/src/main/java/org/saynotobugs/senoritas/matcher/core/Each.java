package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Numbered;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.AllPass;
import org.saynotobugs.senoritas.verdict.FailPrepended;


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
        super(actual -> new AllPass("values [", ",", "]",
                new Mapped<>(
                    numberedVerdict -> new FailPrepended(new TextDescription(numberedVerdict.left() + ": "), numberedVerdict.right()),
                    new Numbered<>(new Mapped<>(delegate::match, actual)))),
            new Composite(new TextDescription(description), delegate.expectation()));
    }
}
