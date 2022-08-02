package org.saynotobugs.confidence.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.assessment.FailPrepended;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;


@StaticFactories("Core")
public final class Throwing implements Quality<Throwing.Breakable>
{
    public interface Breakable
    {
        void run() throws Throwable;
    }


    private final Quality<? super Throwable> mDelegate;


    public Throwing(Class<? extends Throwable> exception)
    {
        this(new InstanceOf<>(exception));
    }


    public Throwing(Quality<? super Throwable> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public Assessment assessmentOf(Throwing.Breakable candidate)
    {
        try
        {
            candidate.run();
            return new Fail(new Delimited(new TextDescription("did not throw"), mDelegate.description()));
        }
        catch (Throwable e)
        {
            return new FailPrepended(new TextDescription("threw"), mDelegate.assessmentOf(e));
        }
    }


    @Override
    public Description description()
    {
        return new Delimited(new TextDescription("throws"), mDelegate.description());
    }
}
