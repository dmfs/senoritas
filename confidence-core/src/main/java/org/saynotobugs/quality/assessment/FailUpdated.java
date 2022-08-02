package org.saynotobugs.quality.assessment;

import org.dmfs.jems2.Function;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Assessment;

import static org.saynotobugs.quality.description.LiteralDescription.EMPTY;


public final class FailUpdated implements Assessment
{
    private final Function<Description, ? extends Description> mDescriptionFunction;
    private final Assessment mDelegate;


    public FailUpdated(Function<Description, ? extends Description> descriptionFunction, Assessment delegate)
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
