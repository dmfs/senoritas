package org.saynotobugs.quality.assessment;

import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;


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
