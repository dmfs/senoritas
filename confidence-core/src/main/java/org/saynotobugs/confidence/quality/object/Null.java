package org.saynotobugs.confidence.quality.object;

import org.dmfs.srcless.annotations.staticfactory.StaticFactory;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.quality.composite.QualityComposition;

import static org.saynotobugs.confidence.description.LiteralDescription.NULL;


/**
 * A {@link Quality} that passes if the actual value is {@code null} and fails otherwise.
 */
public final class Null extends QualityComposition<Object>
{
    /**
     * Creates a {@link Quality} that passes if the value under test is {@code null}.
     */
    @StaticFactory(value = "Core", methodName = "nullValue", packageName = "org.saynotobugs.confidence.quality")
    public Null()
    {
        super(new Satisfies<>(actual -> actual == null, NULL));
    }
}
