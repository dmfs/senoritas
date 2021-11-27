package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Pass;


public final class Anything extends DelegatingMatcher<Object>
{
    public Anything()
    {
        super(actual -> new Pass(), new TextDescription("<anything>"));
    }
}
