package org.saynotobugs.quality.quality.core;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.assessment.FailPrepended;


@StaticFactories("Core")
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
