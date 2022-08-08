package org.saynotobugs.confidence.mockito4.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.mockito.ArgumentMatcher;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.Satisfies;


@StaticFactories(value = "Mockito4", packageName = "org.saynotobugs.confidence.mockito4")
public final class Matches<T> extends QualityComposition<ArgumentMatcher<T>>
{
    /**
     * {@link Quality} of an {@link ArgumentMatcher} that matches the given argument.
     */
    public Matches(T argument)
    {
        super(new Satisfies<>(argumentMatcher -> argumentMatcher.matches(argument),
            new Delimited(new TextDescription("matches"), new ValueDescription(argument))));
    }
}
