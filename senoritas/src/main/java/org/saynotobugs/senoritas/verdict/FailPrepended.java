package org.saynotobugs.senoritas.verdict;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Composite;


public final class FailPrepended extends DelegatingVerdict
{
    public FailPrepended(Description prefix, Verdict delegate)
    {
        super(new FailUpdated(original -> new Composite(prefix, original), delegate));
    }
}
