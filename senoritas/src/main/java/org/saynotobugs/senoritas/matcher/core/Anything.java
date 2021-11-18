package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Pass;


public final class Anything<T> extends DelegatingMatcher<T>
{
    public Anything()
    {
        super(actual -> new Pass(), new TextDescription("<anything>"));
    }
}
