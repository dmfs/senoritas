package org.saynotobugs.confidence.quality.composite;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.FailPrepended;
import org.saynotobugs.confidence.assessment.FailUpdated;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class Has<T, V> extends QualityComposition<T>
{

    public Has(Function<? super T, ? extends V> featureFunction, Quality<? super V> delegate)
    {
        super(actual -> delegate.assessmentOf(featureFunction.value(actual)),
            delegate.description());
    }


    public Has(String featureName, Function<? super T, ? extends V> featureFunction, Quality<? super V> delegate)
    {
        this(new Delimited(new TextDescription("has"), new TextDescription(featureName)),
            new Delimited(new TextDescription("had"), new TextDescription(featureName)),
            featureFunction,
            delegate);
    }


    public Has(Description featureDescription,
        Description featureMismatchDescription,
        Function<? super T, ? extends V> featureFunction,
        Quality<? super V> delegate)
    {
        super(actual -> new FailPrepended(featureMismatchDescription, delegate.assessmentOf(featureFunction.value(actual))),
            new Delimited(featureDescription, delegate.description()));
    }


    public Has(Function<Description, Description> featureDescription,
        Function<Description, Description> featureMismatchDescription,
        Function<? super T, ? extends V> featureFunction,
        Quality<? super V> delegate)
    {
        super(actual -> new FailUpdated(featureMismatchDescription, delegate.assessmentOf(featureFunction.value(actual))),
            featureDescription.value(delegate.description()));
    }
}
