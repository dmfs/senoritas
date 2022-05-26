package org.saynotobugs.senoritas.verdict;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;


/**
 * An Unconditional fail {@link Verdict}.
 */
public final class Fail implements Verdict
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
