package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;


/**
 * A plain text {@link Description}.
 * <p>
 * Note that certain control characters and {@code /} will be escaped in the result.
 */
public final class TextDescription extends DescriptionComposition
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
