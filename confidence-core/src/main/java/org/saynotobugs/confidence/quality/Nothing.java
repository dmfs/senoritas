package org.saynotobugs.confidence.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.description.TextDescription;


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
