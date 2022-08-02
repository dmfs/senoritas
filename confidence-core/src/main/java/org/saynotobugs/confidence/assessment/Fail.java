package org.saynotobugs.confidence.assessment;

import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;


/**
 * An Unconditional fail {@link Assessment}.
 */
public final class Fail implements Assessment
{
    private final Description mMismatch;


    public Fail(Description mismatch)
    {
        mMismatch = mismatch;
    }


    @Override
    public boolean isSuccess()
    {
        return false;
    }


    @Override
    public Description description()
    {
        return mMismatch;
    }
}
