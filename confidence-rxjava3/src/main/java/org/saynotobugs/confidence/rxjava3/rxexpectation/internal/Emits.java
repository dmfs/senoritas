package org.saynotobugs.confidence.rxjava3.rxexpectation.internal;

import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.FailPrepended;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.NumberDescription;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.rxjava3.RxTestAdapter;

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
