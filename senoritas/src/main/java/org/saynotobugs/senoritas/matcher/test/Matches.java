package org.saynotobugs.senoritas.matcher.test;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.*;
import org.saynotobugs.senoritas.verdict.AllPassed;
import org.saynotobugs.senoritas.verdict.MismatchUpdated;

import static org.saynotobugs.senoritas.description.LiteralDescription.EMPTY;
import static org.saynotobugs.senoritas.description.LiteralDescription.NEW_LINE;


@StaticFactories("Test")
public final class Matches<T> implements Matcher<Matcher<? super T>>
{
    private final Iterable<? extends T> mMatchingValues;


    @SafeVarargs
    public Matches(T... matchingValues)
    {
        this(new Seq<>(matchingValues));
    }


    public Matches(Iterable<? extends T> matchingValues)
    {
        mMatchingValues = matchingValues;
    }


    @Override
    public Verdict match(Matcher<? super T> actual)
    {
        return new AllPassed(new TextDescription("matched"), new Composite(NEW_LINE, new TextDescription("and"), NEW_LINE), EMPTY,
            new Mapped<>(
                value -> new MismatchUpdated(
                    orig -> new Delimited(new ValueDescription(value), new TextDescription("mismatched with"), new DescriptionDescription(orig)),
                    actual.match(value)),
                mMatchingValues
            ));
    }


    @Override
    public Description expectation()
    {
        return new StructuredDescription(new TextDescription("matches"), new Composite(NEW_LINE, new TextDescription("and"), NEW_LINE), EMPTY,
            new Mapped<>(ValueDescription::new, mMatchingValues));
    }
}
