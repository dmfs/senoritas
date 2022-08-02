package org.saynotobugs.confidence.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactory;
import org.saynotobugs.confidence.Quality;

import static org.saynotobugs.confidence.description.LiteralDescription.NULL;


/**
 * A {@link Quality} that passes if the actual value is {@code null} and fails otherwise.
 */
public final class Null extends QualityComposition<Object>
{
    /**
     * Creates a {@link Quality} that passes if the value under test is {@code null}.
     */
    @StaticFactory(value = "Core", methodName = "nullValue")
    public Null()
    {
        super(new Satisfies<>(actual -> actual == null, NULL));
    }
}
