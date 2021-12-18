package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactory;
import org.saynotobugs.senoritas.Matcher;

import static org.saynotobugs.senoritas.description.LiteralDescription.NULL;


/**
 * A {@link Matcher} that passes if the actual value is {@code null} and fails otherwise.
 */
public final class Null extends MatcherComposition<Object>
{
    /**
     * Creates a {@link Matcher} that passes if the value under test is {@code null}.
     */
    @StaticFactory(value = "Core", methodName = "isNull")
    public Null()
    {
        super(new Satisfies<>(actual -> actual == null, NULL));
    }
}
