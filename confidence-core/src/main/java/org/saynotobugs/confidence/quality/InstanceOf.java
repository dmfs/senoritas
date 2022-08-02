package org.saynotobugs.confidence.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.PassIf;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;


@StaticFactories("Core")
public final class InstanceOf<T> extends QualityComposition<T>
{
    /**
     * Creates a {@link Quality} that matches whether the object under test is an instance of the given class.
     */
    public InstanceOf(Class<? extends T> expectation)
    {
        super(actual -> new PassIf(
                expectation.isInstance(actual),
                new Delimited(new TextDescription("instance of"), new ValueDescription(actual.getClass()))),
            new Delimited(new TextDescription("instance of"), new ValueDescription(expectation)));
    }
}
