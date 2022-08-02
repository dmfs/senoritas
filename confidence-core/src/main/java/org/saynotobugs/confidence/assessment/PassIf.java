package org.saynotobugs.confidence.assessment;

import org.dmfs.jems2.Single;
import org.saynotobugs.confidence.Description;


public final class PassIf extends AssessmentComposition
{
    public PassIf(boolean result, Description mismatch)
    {
        this(result, () -> mismatch);
    }


    public PassIf(boolean result, Single<Description> mismatch)
    {
        super(result ? new Pass() : new Fail(mismatch.value()));
    }
}
