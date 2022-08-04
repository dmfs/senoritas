package org.saynotobugs.confidence.quality.optional;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.Satisfies;

import java.util.Optional;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
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
