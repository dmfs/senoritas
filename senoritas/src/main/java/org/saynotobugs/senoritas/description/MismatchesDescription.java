package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.comparator.By;
import org.dmfs.jems2.iterable.Clustered;
import org.dmfs.jems2.iterable.Mapped;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Verdict;

import static org.saynotobugs.senoritas.description.LiteralDescription.NEW_LINE;


/**
 * A {@link Description} that contains the mismatch descriptions of failing {@link Verdict}s.
 * <p>
 * Passing {@link Verdict}s are collapsed into {@code ...}.
 */
public final class MismatchesDescription extends DescriptionComposition
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
