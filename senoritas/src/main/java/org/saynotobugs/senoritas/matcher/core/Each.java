package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Numbered;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.verdict.AllPass;
import org.saynotobugs.senoritas.verdict.Updated;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;


public final class Each<T> extends DelegatingMatcher<Iterable<T>>
{

    public Each(Matcher<? super T> delegate)
    {
        this("each value", delegate);
    }


    public Each(String description, Matcher<? super T> delegate)
    {
        super(actual -> new AllPass("values [", ",", "]",
                new Mapped<>(
                    numberedVerdict -> new Updated(orig -> new Composite(new TextDescription(numberedVerdict.left() + ": "), orig), numberedVerdict.right()),
                    new Numbered<>(new Mapped<>(delegate::match, actual)))),
            new Composite(new TextDescription(description), delegate.expectation()));
    }
}
