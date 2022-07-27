package org.saynotobugs.quality.quality.core;

import org.dmfs.jems2.Function;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.Assessment;


/**
 * A {@link Quality} for easy composition.
 */
public abstract class QualityComposition<T> implements Quality<T>
{
    private final Quality<T> mDelegate;


    public QualityComposition(Function<? super T, ? extends Assessment> testFunction, Description expectation)
    {
        this(new Quality<T>()
        {
            @Override
            public Assessment assessmentOf(T candidate)
            {
                return testFunction.value(candidate);
            }


            @Override
            public Description description()
            {
                return expectation;
            }
        });
    }


    public QualityComposition(Quality<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final Assessment assessmentOf(T candidate)
    {
        return mDelegate.assessmentOf(candidate);
    }


    @Override
    public final Description description()
    {
        return mDelegate.description();
    }
}
