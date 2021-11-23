package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.NullDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.PassIf;


public final class Null extends DelegatingMatcher<Object>
{
    public Null()
    {
        super(actual -> new PassIf(actual == null, new ValueDescription<>(actual)),
            new Composite(new NullDescription()));
    }
}
