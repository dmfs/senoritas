package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.Function;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;


@StaticFactories("Core")
public final class Having<T, V> extends DelegatingMatcher<T>
{
    public Having(String featureName, Function<? super T, ? extends V> featureFunction, Matcher<? super V> delegate)
    {
        this(new Delimited(new TextDescription("having"), new TextDescription(featureName)),
            new Delimited(new TextDescription("having"), new TextDescription(featureName)),
            featureFunction,
            delegate);
    }


    public Having(Description featureDescription,
        Description featureMismatchDescription,
        Function<? super T, ? extends V> featureFunction,
        Matcher<? super V> delegate)
    {
        super(actual -> new MismatchPrepended(featureMismatchDescription, delegate.match(featureFunction.value(actual))),
            new Delimited(featureDescription, delegate.expectation()));
    }
}
