package org.saynotobugs.confidence.description;

import org.dmfs.jems2.comparator.By;
import org.dmfs.jems2.iterable.Clustered;
import org.dmfs.jems2.iterable.Mapped;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;

import static org.saynotobugs.confidence.description.LiteralDescription.NEW_LINE;


/**
 * A {@link Description} that contains the mismatch descriptions of failing {@link Assessment}s.
 * <p>
 * Successful {@link Assessment}s are collapsed into {@code ...}.
 */
public final class FailDescription extends DescriptionComposition
{
    public FailDescription(Description entry, Description delimiter, Description exit, Iterable<Assessment> verdicts)
    {
        super(new StructuredDescription(
            entry,
            NEW_LINE,
            exit,
            new Mapped<>(
                cluster -> cluster.iterator().next().isSuccess()
                    ? new TextDescription("...")
                    : new Delimited(delimiter, new Mapped<>(Assessment::description, cluster)),
                new Clustered<>(new By<>(Assessment::isSuccess), verdicts))));
    }
}
