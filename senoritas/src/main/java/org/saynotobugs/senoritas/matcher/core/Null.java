package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.PassIf;

import static org.saynotobugs.senoritas.description.LiteralDescription.NULL;


/**
 * A {@link Matcher} that passes if the actual value is {@code null} and fails otherwise.
 */
public final class Null extends DelegatingMatcher<Object>
{
    public Null()
    {
        super(actual -> new PassIf(actual == null, new ValueDescription(actual)), NULL);
    }
}
