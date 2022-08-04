package org.saynotobugs.confidence.mockito4;

import org.mockito.ArgumentMatcher;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.Satisfies;


public final class Matches<T> extends QualityComposition<ArgumentMatcher<T>>
{
    public Matches(T argument)
    {
        super(new Satisfies<>(argumentMatcher -> argumentMatcher.matches(argument),
            new Delimited(new TextDescription("matches"), new ValueDescription(argument))));
    }
}
