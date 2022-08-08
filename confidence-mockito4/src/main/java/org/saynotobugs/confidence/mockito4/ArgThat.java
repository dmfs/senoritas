package org.saynotobugs.confidence.mockito4;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.mockito.ArgumentMatcher;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.scribe.StringBuilderScribe;


/**
 * An {@link ArgumentMatcher} that matches when the tested argument has the given {@link Quality}.
 */
@StaticFactories(value = "Mockito4", packageName = "org.saynotobugs.confidence.mockito4")
public final class ArgThat<T> implements ArgumentMatcher<T>
{
    private final Quality<? super T> mDelegate;


    public ArgThat(Quality<? super T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public boolean matches(T argument)
    {
        return mDelegate.assessmentOf(argument).isSuccess();
    }


    @Override
    public String toString()
    {
        StringBuilderScribe sb = new StringBuilderScribe("  ");
        mDelegate.description().describeTo(sb);
        return "ArgThat " + sb;
    }
}
