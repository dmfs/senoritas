package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;


@StaticFactories("Core")
public final class Throwing implements Matcher<Throwing.Breakable>
{
    public interface Breakable
    {
        void run() throws Throwable;
    }


    private final Matcher<? super Throwable> mDelegate;


    public Throwing(Class<? extends Throwable> exception)
    {
        this(new InstanceOf<>(exception));
    }


    public Throwing(Matcher<? super Throwable> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Verdict match(Throwing.Breakable actual)
    {
        try
        {
            actual.run();
            return new Fail(new Delimited(new TextDescription("did not throw"), mDelegate.expectation()));
        }
        catch (Throwable e)
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
