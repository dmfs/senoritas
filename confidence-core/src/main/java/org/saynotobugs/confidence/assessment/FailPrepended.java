package org.saynotobugs.confidence.assessment;

import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.description.Delimited;


/**
 * A {@link Assessment} that prepends any mismatch {@link Description}.
 */
public final class FailPrepended extends AssessmentComposition
{
    public FailPrepended(Description prefix, Assessment delegate)
    {
        super(new FailUpdated(original -> new Delimited(prefix, original), delegate));
    }
}
