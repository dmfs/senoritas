package org.saynotobugs.senoritas.verdict;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;


/**
 * A {@link Verdict} that prepends any mismatch {@link Description}.
 */
public final class MismatchPrepended extends DelegatingVerdict
{
    public MismatchPrepended(Description prefix, Verdict delegate)
    {
        super(new MismatchUpdated(original -> new Delimited(prefix, original), delegate));
    }
}
