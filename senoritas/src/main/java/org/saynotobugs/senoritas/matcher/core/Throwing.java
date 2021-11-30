package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.Fragile;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;


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
            return new Fail(new Delimited(new TextDescription("did not throw"), mDelegate.expectation()));
        }
        catch (Exception e)
        {
            return new MismatchPrepended(new TextDescription("threw"), mDelegate.match(e));
        }
    }


    @Override
    public Description expectation()
    {
        return new Delimited(new TextDescription("throws"), mDelegate.expectation());
    }
}
