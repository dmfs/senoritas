package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.single.Backed;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Fail;


public final class Contains<T> extends DelegatingMatcher<Iterable<T>>
{
    public Contains(T value)
    {
        this(new EqualTo<>(value));
    }


    public Contains(Matcher<? super T> delegate)
    {
        super(actual -> new Backed<>(
                new First<>(Verdict::isSuccess, new Mapped<>(delegate::match, actual)),
                new Fail(new Composite(new TextDescription("did not contain"), delegate.expectation())))
                .value(),
            new Composite(new TextDescription("contains"), delegate.expectation()));
    }
}
