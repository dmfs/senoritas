package org.saynotobugs.quality.assessment;

import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.description.Delimited;


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
