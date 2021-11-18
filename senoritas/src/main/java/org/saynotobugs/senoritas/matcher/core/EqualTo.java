package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.Updated;
import org.saynotobugs.senoritas.verdict.PassIf;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;


public final class EqualTo<T> extends DelegatingMatcher<T>
{
    public EqualTo(T expected)
    {
        super(actual -> attestation(expected, actual),
            new ValueDescription<>(expected));
    }


    private static <T> Verdict attestation(T expected, T actual)
    {
        return expected.getClass().isArray()
            ? new Updated(orig -> new Composite(new TextDescription("array that"), orig),
            new Iterates<>(new Mapped<>(EqualTo::new, new Seq<>((T[]) expected))).match(new Seq<T>((T[]) actual)))
            : new PassIf(expected.equals(actual), new ValueDescription<>(actual));
    }
}
