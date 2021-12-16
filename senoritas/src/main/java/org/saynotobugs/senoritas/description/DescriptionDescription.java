package org.saynotobugs.senoritas.description;

import org.saynotobugs.senoritas.Description;

import static org.saynotobugs.senoritas.description.LiteralDescription.NEW_LINE;


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
