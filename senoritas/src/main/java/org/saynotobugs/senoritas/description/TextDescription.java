package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;


public final class TextDescription implements Description
{
    private final String mText;


    public TextDescription(String text)
    {
        this.mText = text;
    }


    @Override
    public void describeTo(Scribe sink)
    {
        sink.append(mText);
    }
}
