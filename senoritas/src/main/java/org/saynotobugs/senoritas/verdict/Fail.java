package org.saynotobugs.senoritas.verdict;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;


public final class Fail implements Verdict
{
    private final Description mDiff;


    public Fail(Description diff)
    {
        mDiff = diff;
    }


    @Override
    public boolean isSuccess()
    {
        return false;
    }


    @Override
    public Description description()
    {
        return mDiff;
    }

}
