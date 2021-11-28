package org.saynotobugs.senoritas.description;

public final class TextDescription extends DelegatingDescription
{
    public TextDescription(String text)
    {
        super(scribe -> scribe.append(text));
    }
}
