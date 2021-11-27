package org.saynotobugs.senoritas.matcher.matcher;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.*;
import org.saynotobugs.senoritas.verdict.AllPass;
import org.saynotobugs.senoritas.verdict.FailUpdated;


public final class Matches<T> implements Matcher<Matcher<T>>
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
    public Verdict match(Matcher<T> actual)
    {
        return new AllPass("[", ",", "]",
            new Mapped<>(
                value -> new FailUpdated(
                    orig -> new Composite(new ValueDescription<>(value), new TextDescription("mismatched with"), new DescriptionDescription(orig)),
                    actual.match(value)),
                mMatchingValues
            ));
    }


    @Override
    public Description expectation()
    {
        return new Composite(new TextDescription("matches"), new StructuredDescription("[", ",", "]", new Mapped<>(ValueDescription::new, mMatchingValues)));
    }
}
