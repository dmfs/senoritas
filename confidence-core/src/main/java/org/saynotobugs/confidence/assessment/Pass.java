package org.saynotobugs.confidence.assessment;

import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;

import static org.saynotobugs.confidence.description.LiteralDescription.EMPTY;


/**
 * An unconditional pass {@link Assessment}.
 */
public final class Pass implements Assessment
{
    @Override
    public boolean isSuccess()
    {
        return true;
    }


    @Override
    public Description description()
    {
        return EMPTY;
    }
}
