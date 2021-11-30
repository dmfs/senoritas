package org.saynotobugs.senoritas.description;

public final class TextDescription extends DelegatingDescription
{
    public TextDescription(CharSequence text)
    {
        super(scribe -> scribe.append(text.toString()
            .replace("\\", "\\\\")
            .replace("\t", "\\t")
            .replace("\b", "\\b")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\f", "\\f")
            .replace("\"", "\\\"")));
    }
}
