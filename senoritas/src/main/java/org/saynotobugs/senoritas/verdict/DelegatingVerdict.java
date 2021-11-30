package org.saynotobugs.senoritas.verdict;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;


/**
 * A {@link Verdict} for easy composition.
 */
public abstract class DelegatingVerdict implements Verdict
{
    private final Verdict mDelegate;


    public DelegatingVerdict(Verdict delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public final boolean isSuccess()
    {
        return mDelegate.isSuccess();
    }


    @Override
    public final Description description()
    {
        return mDelegate.description();
    }
}
