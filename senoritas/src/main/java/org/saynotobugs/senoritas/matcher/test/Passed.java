package org.saynotobugs.senoritas.matcher.test;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.DelegatingMatcher;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;


@StaticFactories("Test")
public final class Passed extends DelegatingMatcher<Verdict>
{

    public Passed()
    {
        super(actual -> actual.isSuccess()
                ? new MismatchPrepended(new TextDescription("passed but did described mismatch"), new DescribesAs("").match(actual.description()))
                : new Fail(new Delimited("failed with", actual.description())),
            new TextDescription("passes"));
    }
}
