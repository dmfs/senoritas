package org.saynotobugs.quality.quality.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;
import org.saynotobugs.quality.assessment.PassIf;


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
