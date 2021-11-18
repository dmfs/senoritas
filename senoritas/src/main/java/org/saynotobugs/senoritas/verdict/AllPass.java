package org.saynotobugs.senoritas.verdict;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.StructuredDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.dmfs.jems2.comparator.By;
import org.dmfs.jems2.iterable.Clustered;
import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.predicate.Not;
import org.saynotobugs.senoritas.description.EmptyDescription;


public final class AllPass implements Verdict
{
    private final String mPrefix;
    private final String mDelimiter;
    private final String mSuffix;
    private final Iterable<Verdict> mResults;


    public AllPass(String prefix, String delimiter, Verdict... verdicts)
    {
        this(prefix, delimiter, "", new Seq<>(verdicts));
    }


    public AllPass(String prefix, String delimiter, String suffix, Verdict... verdicts)
    {
        this(prefix, delimiter, suffix, new Seq<>(verdicts));
    }


    public AllPass(String prefix, String delimiter, Iterable<Verdict> results)
    {
        this(prefix, delimiter, "", results);
    }


    public AllPass(String prefix, String delimiter, String suffix, Iterable<Verdict> results)
    {
        mPrefix = prefix;
        mDelimiter = delimiter;
        mResults = results;
        mSuffix = suffix;
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
            ? EmptyDescription.emptyDescription
            : new StructuredDescription(
                mPrefix,
                "",
                mSuffix,
                new Mapped<>(
                    cluster -> cluster.iterator().next().isSuccess()
                        ? new TextDescription("...")
                        : new StructuredDescription(mDelimiter, new Mapped<>(Verdict::description, cluster)),
                    new Clustered<>(new By<>(Verdict::isSuccess), mResults)));

    }

}
