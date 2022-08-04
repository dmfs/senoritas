package org.saynotobugs.confidence.quality.composite;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.FailUpdated;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class DescribedAs<T> extends QualityComposition<T>
{
    public DescribedAs(
        Function<Description, ? extends Description> descriptionUpdate,
        Quality<T> delegate)
    {
        this(descriptionUpdate, descriptionUpdate, delegate);
    }


    public DescribedAs(
        Function<Description, ? extends Description> mismatchUpdate,
        Function<Description, ? extends Description> expectationUpdate,
        Quality<T> delegate)
    {
        super(
            actual -> new FailUpdated(mismatchUpdate, delegate.assessmentOf(actual)),
            expectationUpdate.value(delegate.description()));
    }
}
