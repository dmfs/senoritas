package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.Fragile;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.FailPrepended;


public final class Throwing implements Matcher<Fragile<?, ?>>
{
    private final Matcher<? super Exception> mDelegate;


    public Throwing(Matcher<? super Exception> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Verdict match(Fragile<?, ?> actual)
    {
        try
        {
            actual.value();
            return new Fail(new Composite(new TextDescription("did not throw"), mDelegate.expectation()));
        }
        catch (Exception e)
        {
            return new FailPrepended(new TextDescription("threw"), mDelegate.match(e));
        }
    }


    @Override
    public Description expectation()
    {
        return new Composite(new TextDescription("throws"), mDelegate.expectation());
    }
}
