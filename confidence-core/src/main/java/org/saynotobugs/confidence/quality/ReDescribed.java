package org.saynotobugs.confidence.quality;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.FailUpdated;


@StaticFactories("Core")
public final class ReDescribed<T> extends QualityComposition<T>
{
    public ReDescribed(
        Function<Description, ? extends Description> descriptionUpdate,
        Quality<T> delegate)
    {
        this(descriptionUpdate, descriptionUpdate, delegate);
    }


    public ReDescribed(
        Function<Description, ? extends Description> mismatchUpdate,
        Function<Description, ? extends Description> expectationUpdate,
        Quality<T> delegate)
    {
        super(
            actual -> new FailUpdated(mismatchUpdate, delegate.assessmentOf(actual)),
            expectationUpdate.value(delegate.description()));
    }
}
