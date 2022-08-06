package org.saynotobugs.confidence.quality.optional;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.assessment.FailPrepended;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.EqualTo;
import org.saynotobugs.confidence.quality.trivial.Anything;

import java.util.Optional;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class Present<T> extends QualityComposition<Optional<T>>
{
    /**
     * Matches present {@link Optional}s ith any value.
     */
    public Present()
    {
        this(new Anything());
    }


    /**
     * Matches present {@link Optional}s with a value that's equal to the given one.
     */
    public Present(T value)
    {
        this(new EqualTo<>(value));
    }


    /**
     * Matches present {@link Optional}s with a value that matches the given matcher.
     */
    public Present(Quality<? super T> delegate)
    {
        super(actual -> actual.isPresent()
                ? new FailPrepended(new TextDescription("was present"), delegate.assessmentOf(actual.get()))
                : new Fail(new TextDescription("was absent")),
            new Delimited(new TextDescription("is present"), delegate.description()));
    }
}
