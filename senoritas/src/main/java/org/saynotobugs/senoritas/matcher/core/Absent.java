package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.Pass;

import java.util.Optional;


public final class Absent<T> extends DelegatingMatcher<Optional<T>>
{
    public Absent()
    {
        super(actual -> !actual.isPresent()
                ? new Pass()
                : new Fail(new ValueDescription(actual)),
            new TextDescription("<empty> optional"));
    }
}
