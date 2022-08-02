package org.saynotobugs.confidence.description;

import org.saynotobugs.confidence.Description;

import static org.saynotobugs.confidence.description.LiteralDescription.NEW_LINE;


/**
 * A {@link Description} of a {@link Description}.
 */
public final class DescriptionDescription extends DescriptionComposition
{

    public DescriptionDescription(Description description)
    {
        super(new Indented(new Composite(NEW_LINE, new TextDescription("----"), NEW_LINE, description, NEW_LINE, new TextDescription("----"))));
    }
}
