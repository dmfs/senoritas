package org.saynotobugs.quality.quality.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.hamcrest.StringDescription;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.assessment.Fail;
import org.saynotobugs.quality.assessment.Pass;


@StaticFactories("Core")
public final class Hamcrest<T> implements Quality<T>
{
    private final org.hamcrest.Matcher<? super T> mDelegate;


    public Hamcrest(org.hamcrest.Matcher<? super T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Assessment assessmentOf(T candidate)
    {
        if (mDelegate.matches(candidate))
        {
            return new Pass();
        }
        org.hamcrest.Description mismatch = new StringDescription();
        mDelegate.describeMismatch(candidate, mismatch);
        return new Fail(new TextDescription(mismatch.toString()));
    }


    @Override
    public Description description()
    {
        org.hamcrest.Description expected = new StringDescription();
        mDelegate.describeTo(expected);
        return new TextDescription(expected.toString());
    }

}
