package org.saynotobugs.quality.assessment;

import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;

import static org.saynotobugs.quality.description.LiteralDescription.EMPTY;


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
