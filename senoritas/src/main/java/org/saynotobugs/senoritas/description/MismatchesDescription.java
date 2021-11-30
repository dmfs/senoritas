package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.comparator.By;
import org.dmfs.jems2.iterable.Clustered;
import org.dmfs.jems2.iterable.Mapped;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;

import static org.saynotobugs.senoritas.description.LiteralDescription.EMPTY;
import static org.saynotobugs.senoritas.description.LiteralDescription.NEW_LINE;


public final class MismatchesDescription extends DelegatingDescription
{
    public MismatchesDescription(Description entry, Description delimiter, Description exit, Iterable<Verdict> verdicts)
    {
        super(new StructuredDescription(
            entry,
            NEW_LINE,
            exit,
            new Mapped<>(
                cluster -> cluster.iterator().next().isSuccess()
                    ? new TextDescription("...")
                    : new Delimited(delimiter, new Mapped<>(Verdict::description, cluster)),
                new Clustered<>(new By<>(Verdict::isSuccess), verdicts))));
    }
}
