package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.description.ValueDescription;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.Pass;

import java.util.Optional;


public final class Absent<T> extends DelegatingMatcher<Optional<T>>
{
    public Absent()
    {
        super(actual -> actual.isEmpty()
                ? new Pass()
                : new Fail(new Composite(new TextDescription("present"), new ValueDescription<>(actual.get()))),
            new TextDescription("empty optional"));
    }
}
