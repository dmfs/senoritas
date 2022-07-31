package org.saynotobugs.quality.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.description.TextDescription;

import java.util.Optional;


@StaticFactories("Core")
public final class Absent<T> extends QualityComposition<Optional<T>>
{
    /**
     * Matches empty {@link Optional}s.
     */
    public Absent()
    {
        super(new Satisfies<>(
            actual -> !actual.isPresent(),
            new TextDescription("<empty> optional")));
    }
}