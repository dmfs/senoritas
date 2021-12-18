package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.utils.ArrayIterable;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;
import org.saynotobugs.senoritas.verdict.PassIf;


@StaticFactories("Core")
public final class EqualTo<T> extends MatcherComposition<T>
{
    /**
     * Creates a {@link Matcher} that matches if the value under test is equal to the given value.
     */
    public EqualTo(T expected)
    {
        super(actual -> attestation(expected, actual),
            new ValueDescription(expected));
    }


    private static <T> Verdict attestation(T expected, T actual)
    {
        return expected.getClass().isArray() && actual.getClass().isArray()
            ? new MismatchPrepended(new TextDescription("array that"),
            new Iterates<>(new Mapped<>(EqualTo::new, new ArrayIterable(expected))).match(new ArrayIterable(actual)))
            : new PassIf(expected.equals(actual), new ValueDescription(actual));
    }

}
