package org.saynotobugs.senoritas.matcher.test;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.matcher.core.Anything;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.MismatchUpdated;


@StaticFactories("Test")
public final class Mismatches<T> implements Matcher<Matcher<? super T>>
{
    private final T mMismatchingValue;
    private final Matcher<? super Description> mDiffMatcher;


    public Mismatches(T mismatchingValue)
    {
        this(mismatchingValue, new Anything());
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
    public Verdict match(Matcher<? super T> actual)
    {
        Verdict matcherVerdict = actual.match(mMismatchingValue);
        return matcherVerdict.isSuccess()
            ? new Fail(
            new Delimited(
                new ValueDescription(mMismatchingValue),
                new TextDescription("matched"),
                actual.expectation()))
            : new MismatchUpdated(
                orig -> new Delimited(
                    new ValueDescription(mMismatchingValue),
                    new TextDescription("mismatched with diff"),
                    orig),
                mDiffMatcher.match(matcherVerdict.description()));
    }


    @Override
    public Description expectation()
    {
        return new Delimited(
            new TextDescription("mismatches"),
            new ValueDescription(mMismatchingValue),
            new TextDescription("with diff"),
            mDiffMatcher.expectation());
    }
}
