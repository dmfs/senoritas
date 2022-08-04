package org.saynotobugs.confidence.quality.compat;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.hamcrest.StringDescription;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.assessment.Pass;
import org.saynotobugs.confidence.description.TextDescription;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
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
