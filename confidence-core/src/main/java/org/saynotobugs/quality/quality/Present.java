package org.saynotobugs.quality.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.assessment.Fail;
import org.saynotobugs.quality.assessment.FailPrepended;

import java.util.Optional;


@StaticFactories("Core")
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