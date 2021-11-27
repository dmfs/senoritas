package org.saynotobugs.senoritas.matcher.matcher;

import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.FailUpdated;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.DescriptionDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.matcher.core.Anything;


public final class Mismatches<T> implements Matcher<Matcher<T>>
{
    private final T mMismatchingValue;
    private final Matcher<? super Description> mDiffMatcher;


    public Mismatches(T mismatchingValue)
    {
        this(mismatchingValue, new Anything<>());
    }


    public Mismatches(T mismatchingValue, String description)
    {
        this(mismatchingValue, new DescribesAs(description));
    }


    public Mismatches(T mismatchingValue, Matcher<? super Description> diffMatcher)
    {
        mMismatchingValue = mismatchingValue;
        mDiffMatcher = diffMatcher;
    }


    @Override
    public Verdict match(Matcher<T> actual)
    {
        Verdict matcherVerdict = actual.match(mMismatchingValue);
        return matcherVerdict.isSuccess()
            ? new Fail(
            new Composite(
                new ValueDescription<>(mMismatchingValue),
                new TextDescription("matched"),
                new DescriptionDescription(actual.expectation())))
            : new FailUpdated(
                orig -> new Composite(
                    new ValueDescription<>(mMismatchingValue),
                    new TextDescription("mismatched with diff"),
                    new DescriptionDescription(orig)),
                mDiffMatcher.match(matcherVerdict.description()));
    }


    @Override
    public Description expectation()
    {
        return new Composite(
            new TextDescription("mismatches"),
            new ValueDescription<>(mMismatchingValue),
            new TextDescription("with diff"),
            new DescriptionDescription(mDiffMatcher.expectation()));
    }
}
