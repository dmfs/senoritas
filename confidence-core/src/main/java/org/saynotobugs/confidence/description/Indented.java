package org.saynotobugs.confidence.description;

import org.saynotobugs.confidence.Description;


/**
 * A {@link Description} that increases the level of indentation by one.
 */
public final class Indented extends DescriptionComposition
{
    public Indented(Description delegate)
    {
        super(scribe -> delegate.describeTo(scribe.indented()));
    }
}
