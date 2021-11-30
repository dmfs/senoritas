package org.saynotobugs.senoritas.verdict;

import org.saynotobugs.senoritas.Description;


public final class PassIf extends DelegatingVerdict
{
    public PassIf(boolean result, Description mismatch)
    {
        super(result ? new Pass() : new Fail(mismatch));
    }
}
