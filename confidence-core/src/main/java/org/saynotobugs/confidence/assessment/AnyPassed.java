package org.saynotobugs.confidence.assessment;

import org.dmfs.jems2.iterable.Frozen;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.First;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.description.FailDescription;

import static org.saynotobugs.confidence.description.LiteralDescription.EMPTY;


/**
 * A {@link Assessment} that passes when any of the delegate {@link Assessment}s passed.
 */
public final class AnyPassed implements Assessment
{
    private final Description mEntry;
    private final Description mDelimiter;
    private final Description mExit;
    private final Iterable<Assessment> mResults;


    public AnyPassed(Description entry, Description delimiter, Assessment... assessments)
    {
        this(entry, delimiter, EMPTY, new Seq<>(assessments));
    }


    public AnyPassed(Description entry, Description delimiter, Description exit, Assessment... assessments)
    {
        this(entry, delimiter, exit, new Seq<>(assessments));
    }


    public AnyPassed(Description entry, Description delimiter, Iterable<Assessment> results)
    {
        this(entry, delimiter, EMPTY, results);
    }


    public AnyPassed(Description entry, Description delimiter, Description exit, Iterable<Assessment> results)
    {
        mEntry = entry;
        mDelimiter = delimiter;
        mResults = new Frozen<>(results);
        mExit = exit;
    }


    @Override
    public boolean isSuccess()
    {
        return new First<>(Assessment::isSuccess, mResults).isPresent();
    }


    @Override
    public Description description()
    {
        return isSuccess()
            ? EMPTY
            : new FailDescription(mEntry, mDelimiter, mExit, mResults);

    }

}
