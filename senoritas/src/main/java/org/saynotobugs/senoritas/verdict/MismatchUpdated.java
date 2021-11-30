package org.saynotobugs.senoritas.verdict;

import org.dmfs.jems2.Function;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;

import static org.saynotobugs.senoritas.description.LiteralDescription.EMPTY;


public final class MismatchUpdated implements Verdict
{
    private final Function<Description, ? extends Description> mDescriptionFunction;
    private final Verdict mDelegate;


    public MismatchUpdated(Function<Description, ? extends Description> descriptionFunction, Verdict delegate)
    {
        mDescriptionFunction = descriptionFunction;
        mDelegate = delegate;
    }


    @Override
    public boolean isSuccess()
    {
        return mDelegate.isSuccess();
    }


    @Override
    public Description description()
    {
        return isSuccess() ? EMPTY : mDescriptionFunction.value(mDelegate.description());
    }

}
