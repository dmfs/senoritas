package org.saynotobugs.senoritas.verdict;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;

import static org.saynotobugs.senoritas.description.LiteralDescription.EMPTY;


public final class Pass implements Verdict
{
    @Override
    public boolean isSuccess()
    {
        return true;
    }


    @Override
    public Description description()
    {
        return EMPTY;
    }
}
