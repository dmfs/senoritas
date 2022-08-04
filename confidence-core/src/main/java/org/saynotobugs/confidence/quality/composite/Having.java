package org.saynotobugs.confidence.quality.composite;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.FailPrepended;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class Having<T, V> extends QualityComposition<T>
{

    public Having(Function<? super T, ? extends V> featureFunction, Quality<? super V> delegate)
    {
        super(actual -> delegate.assessmentOf(featureFunction.value(actual)),
            delegate.description());
    }


    public Having(String featureName, Function<? super T, ? extends V> featureFunction, Quality<? super V> delegate)
    {
        this(new Delimited(new TextDescription("having"), new TextDescription(featureName)),
            new Delimited(new TextDescription("having"), new TextDescription(featureName)),
            featureFunction,
            delegate);
    }


    public Having(Description featureDescription,
        Description featureMismatchDescription,
        Function<? super T, ? extends V> featureFunction,
        Quality<? super V> delegate)
    {
        super(actual -> new FailPrepended(featureMismatchDescription, delegate.assessmentOf(featureFunction.value(actual))),
            new Delimited(featureDescription, delegate.description()));
    }
}
