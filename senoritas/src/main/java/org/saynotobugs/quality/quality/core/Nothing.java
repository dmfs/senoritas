package org.saynotobugs.quality.quality.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.assessment.Fail;


/**
 * A {@link Quality} that never matches.
 * <p>
 * This may be useful for testing {@link Quality}s.
 */
@StaticFactories("Core")
public final class Nothing extends QualityComposition<Object>
{
    /**
     * Creates a {@link Quality} that never matches.
     */
    public Nothing()
    {
        super(actual -> new Fail(new TextDescription("was something")), new TextDescription("<nothing>"));
    }
}
