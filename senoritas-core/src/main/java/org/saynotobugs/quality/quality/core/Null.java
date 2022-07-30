package org.saynotobugs.quality.quality.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactory;
import org.saynotobugs.quality.Quality;

import static org.saynotobugs.quality.description.LiteralDescription.NULL;


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
