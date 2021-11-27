package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Scribe;
import org.saynotobugs.senoritas.utils.Delimited;


public final class StructuredDescription implements Description
{
    private final String mIntro;
    private final String mDelimiter;
    private final String mOutro;
    private final Iterable<? extends Description> mValue;


    public StructuredDescription(String delimiter, Iterable<? extends Description> value)
    {
        this("", delimiter, "", value);
    }


    public StructuredDescription(String intro, String delimiter, String outro, Iterable<? extends Description> value)
    {
        mIntro = intro;
        mDelimiter = delimiter;
        mOutro = outro;
        mValue = value;
    }


    @Override
    public void describeTo(Scribe scribe)
    {
        Scribe s = (mIntro.length() + mOutro.length() > 0) ? scribe.indented() : scribe;
        new Delimited<Description>(
            () -> {
                if (mIntro.length() != 0)
                {
                    s.append(mIntro).newLine();
                }
            },
            () -> s.append(mDelimiter).newLine(),
            () -> {
                if (mOutro.length() != 0)
                {
                    scribe.newLine().append(mOutro);
                }
            },
            e -> e.describeTo(s)).process(mValue);
    }
}
