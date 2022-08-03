package org.saynotobugs.confidence.description;

import org.dmfs.jems2.Single;
import org.saynotobugs.confidence.Description;


/**
 * A plain text {@link Description}.
 * <p>
 * Note that certain control characters and {@code \} will be escaped in the result.
 */
public final class TextDescription extends DescriptionComposition
{
    public TextDescription(CharSequence text)
    {
        this(text::toString);
    }


    public TextDescription(Single<String> text)
    {
        super(scribe -> scribe.append(text.value()
            .replace("\\", "\\\\")
            .replace("\t", "\\t")
            .replace("\b", "\\b")
            .replace("\n", "\\n")
            .replace("\r", "\\r")
            .replace("\f", "\\f")
            .replace("\"", "\\\"")));
    }
}
