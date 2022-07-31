package org.saynotobugs.senoritas.mockito4;

import org.mockito.ArgumentMatcher;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;
import org.saynotobugs.quality.quality.QualityComposition;
import org.saynotobugs.quality.quality.Satisfies;


public final class Matches<T> extends QualityComposition<ArgumentMatcher<T>>
{
    public Matches(T argument)
    {
        super(new Satisfies<>(argumentMatcher -> argumentMatcher.matches(argument),
            new Delimited(new TextDescription("matches"), new ValueDescription(argument))));
    }
}
