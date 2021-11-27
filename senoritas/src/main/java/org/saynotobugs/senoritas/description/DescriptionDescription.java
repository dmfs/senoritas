package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;


/**
 * A {@link Description} of a {@link Description}.
 */
public final class DescriptionDescription implements Description
{
    private final Description mDescription;


    public DescriptionDescription(Description description)
    {
        mDescription = description;
    }


    @Override
    public void describeTo(Scribe scribe)
    {
        Scribe s = scribe.indented();
        s.newLine().append("----");
        mDescription.describeTo(s.newLine());
        s.newLine().append("----");
    }
}
