package org.saynotobugs.quality.quality.test;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;
import org.saynotobugs.quality.quality.core.Anything;
import org.saynotobugs.quality.assessment.Fail;
import org.saynotobugs.quality.assessment.FailUpdated;


@StaticFactories("Test")
public final class Fails<T> implements Quality<Quality<? super T>>
{
    private final T mMismatchingValue;
    private final Quality<? super Description> mDiffQuality;


    public Fails(T mismatchingValue)
    {
        this(mismatchingValue, new Anything());
    }


    public Fails(T mismatchingValue, String description)
    {
        this(mismatchingValue, new DescribesAs(description));
    }


    public Fails(T mismatchingValue, Quality<? super Description> diffQuality)
    {
        mMismatchingValue = mismatchingValue;
        mDiffQuality = diffQuality;
    }


    @Override
    public Assessment assessmentOf(Quality<? super T> candidate)
    {
        Assessment matcherAssessment = candidate.assessmentOf(mMismatchingValue);
        return matcherAssessment.isSuccess()
            ? new Fail(
            new Delimited(
                new ValueDescription(mMismatchingValue),
                new TextDescription("matched"),
                candidate.description()))
            : new FailUpdated(
                orig -> new Delimited(
                    new ValueDescription(mMismatchingValue),
                    new TextDescription("mismatched with diff"),
                    orig),
                mDiffQuality.assessmentOf(matcherAssessment.description()));
    }


    @Override
    public Description description()
    {
        return new Delimited(
            new TextDescription("mismatches"),
            new ValueDescription(mMismatchingValue),
            new TextDescription("with diff"),
            mDiffQuality.description());
    }
}
