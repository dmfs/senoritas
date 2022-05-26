package org.saynotobugs.senoritas.verdict;

import org.dmfs.jems2.iterable.Frozen;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.predicate.Not;
import org.dmfs.jems2.single.Collected;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.MismatchesDescription;

import java.util.ArrayList;

import static org.saynotobugs.senoritas.description.LiteralDescription.EMPTY;


/**
 * A {@link Verdict} that passes when all the delegate {@link Verdict}s pass.
 */
public final class AllPassed implements Verdict
{
    private final Description mEntry;
    private final Description mDelimiter;
    private final Description mExit;
    private final Iterable<Verdict> mResults;


    public AllPassed(Description entry, Description delimiter, Verdict... verdicts)
    {
        this(entry, delimiter, EMPTY, new Seq<>(verdicts));
    }


    public AllPassed(Description entry, Description delimiter, Description exit, Verdict... verdicts)
    {
        this(entry, delimiter, exit, new Seq<>(verdicts));
    }


    public AllPassed(Description entry, Description delimiter, Iterable<Verdict> results)
    {
        this(entry, delimiter, EMPTY, results);
    }


    public AllPassed(Description entry, Description delimiter, Description exit, Iterable<Verdict> results)
    {
        mEntry = entry;
        mDelimiter = delimiter;
        mResults = new Frozen<>(results);
        mExit = exit;
    }


    @Override
    public boolean isSuccess()
    {
        return !new First<>(new Not<>(Verdict::isSuccess), mResults).isPresent();
    }


    @Override
    public Description description()
    {
        return isSuccess()
            ? EMPTY
            : new MismatchesDescription(mEntry, mDelimiter, mExit, mResults);

    }

}
