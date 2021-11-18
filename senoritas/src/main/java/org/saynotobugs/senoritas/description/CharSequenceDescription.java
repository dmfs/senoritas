package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;


public final class CharSequenceDescription implements Description
{
    private final CharSequence mValue;


    public CharSequenceDescription(CharSequence value)
    {
        mValue = value;
    }


    @Override
    public void describeTo(Scribe sink)
    {
        sink.append("\"")
            .append(mValue)
            .append("\"");
    }
}
