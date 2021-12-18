package org.saynotobugs.senoritas.verdict;

import org.dmfs.jems2.Single;
import org.saynotobugs.senoritas.Description;


public final class PassIf extends VerdictComposition
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
