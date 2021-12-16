package org.saynotobugs.senoritas.matcher.test;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.DelegatingMatcher;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;


@StaticFactories("Test")
public final class Failure extends DelegatingMatcher<Verdict>
{
    public Failure(Matcher<? super Description> mismatchDescription)
    {
        super(actual -> actual.isSuccess()
                ? new Fail(new TextDescription("did pass"))
                : new MismatchPrepended(new TextDescription("mismatched with description"), mismatchDescription.match(actual.description())),
            new Delimited(new TextDescription("failed with"), mismatchDescription.expectation()));
    }
}
