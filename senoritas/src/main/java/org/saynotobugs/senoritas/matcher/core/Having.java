package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.Function;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.FailPrepended;


public final class Having<T, V> extends DelegatingMatcher<T>
{
    public Having(String featureName, Function<? super T, ? extends V> featureFunction, Matcher<? super V> delegate)
    {
        this(new Composite(new TextDescription("having"), new TextDescription(featureName)),
            new Composite(new TextDescription("having"), new TextDescription(featureName)),
            featureFunction,
            delegate);
    }


    public Having(Description featureDescription,
        Description featureMismatchDescription,
        Function<? super T, ? extends V> featureFunction,
        Matcher<? super V> delegate)
    {
        super(actual -> new FailPrepended(featureMismatchDescription, delegate.match(featureFunction.value(actual))),
            new Composite(featureDescription, delegate.expectation()));
    }
}
