package org.saynotobugs.senoritas.verdict;

import org.dmfs.jems2.Function;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.Description;

import static org.saynotobugs.senoritas.description.EmptyDescription.emptyDescription;


public final class FailUpdated implements Verdict
{
    private final Function<Description, ? extends Description> mDescriptionFunction;
    private final Verdict mDelegate;



    public FailUpdated(Function<Description, ? extends Description> descriptionFunction, Verdict delegate)
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
        return isSuccess() ? emptyDescription : mDescriptionFunction.value(mDelegate.description());
    }

}
