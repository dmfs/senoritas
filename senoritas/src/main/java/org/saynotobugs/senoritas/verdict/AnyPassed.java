package org.saynotobugs.senoritas.verdict;

import org.dmfs.jems2.iterable.Frozen;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.single.Collected;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.MismatchesDescription;

import java.util.ArrayList;

import static org.saynotobugs.senoritas.description.LiteralDescription.EMPTY;


/**
 * A {@link Verdict} that passes when any of the delegate {@link Verdict}s passed.
 */
public final class AnyPassed implements Verdict
{
    private final Description mEntry;
    private final Description mDelimiter;
    private final Description mExit;
    private final Iterable<Verdict> mResults;


    public AnyPassed(Description entry, Description delimiter, Verdict... verdicts)
    {
        this(entry, delimiter, EMPTY, new Seq<>(verdicts));
    }


    public AnyPassed(Description entry, Description delimiter, Description exit, Verdict... verdicts)
    {
        this(entry, delimiter, exit, new Seq<>(verdicts));
    }


    public AnyPassed(Description entry, Description delimiter, Iterable<Verdict> results)
    {
        this(entry, delimiter, EMPTY, results);
    }


    public AnyPassed(Description entry, Description delimiter, Description exit, Iterable<Verdict> results)
    {
        mEntry = entry;
        mDelimiter = delimiter;
        mResults = new Frozen<>(results);
        mExit = exit;
    }


    @Override
    public boolean isSuccess()
    {
        return new First<>(Verdict::isSuccess, mResults).isPresent();
    }


    @Override
    public Description description()
    {
        return isSuccess()
            ? EMPTY
            : new MismatchesDescription(mEntry, mDelimiter, mExit, mResults);

    }

}
