package org.saynotobugs.quality.quality.rxjava3.internal;

import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.assessment.FailPrepended;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.NumberDescription;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.rxjava3.adapters.RxTestAdapter;

import java.util.Collection;


public final class Emits<T> implements Quality<RxTestAdapter<T>>
{

    private final int mEmissionCount;
    private final Quality<? super Iterable<T>> mEmissionQualities;


    public Emits(int emissionCount, Quality<? super Iterable<T>> emissionQualities)
    {
        mEmissionCount = emissionCount;
        mEmissionQualities = emissionQualities;
    }


    @Override
    public Assessment assessmentOf(RxTestAdapter<T> candidate)
    {
        candidate.awaitNext(mEmissionCount);
        Collection<T> values = candidate.newValues(mEmissionCount);
        Assessment result = mEmissionQualities.assessmentOf(values);
        if (result.isSuccess())
        {
            candidate.ack(values.size());
        }
        return new FailPrepended(
            new Delimited(
                new TextDescription("emitted"),
                new NumberDescription(values.size()),
                new TextDescription("items that")),
            result);
    }


    @Override
    public Description description()
    {
        return new Delimited(
            new TextDescription("emits"),
            new NumberDescription(mEmissionCount),
            new TextDescription("items that"),
            mEmissionQualities.description());
    }
}
