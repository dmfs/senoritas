package org.saynotobugs.confidence.quality.trivial;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;


/**
 * A {@link Quality} that never matches.
 * <p>
 * This may be useful for testing {@link Quality}s.
 */
@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class Nothing extends QualityComposition<Object>
{
    /**
     * Creates a {@link Quality} that never matches.
     */
    public Nothing()
    {
        super(actual -> new Fail(new ValueDescription(actual)), new TextDescription("<nothing>"));
    }
}
