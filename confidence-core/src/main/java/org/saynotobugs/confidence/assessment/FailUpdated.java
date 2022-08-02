package org.saynotobugs.confidence.assessment;

import org.dmfs.jems2.Function;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Assessment;

import static org.saynotobugs.confidence.description.LiteralDescription.EMPTY;


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
