package org.saynotobugs.quality.quality.core;

import org.dmfs.jems2.generatable.Sequence;
import org.dmfs.jems2.iterable.First;
import org.dmfs.jems2.procedure.ForEach;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;
import org.saynotobugs.quality.assessment.AllPassed;
import org.saynotobugs.quality.assessment.Fail;
import org.saynotobugs.quality.assessment.FailPrepended;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.saynotobugs.quality.description.LiteralDescription.COMMA_NEW_LINE;
import static org.saynotobugs.quality.description.LiteralDescription.EMPTY;


@StaticFactories("Core")
public final class Parallel<T> implements Quality<T>
{
    private final int mThreadCount;
    private final Quality<T> mDelegate;


    public Parallel(Quality<T> delegate)
    {
        this(1000, delegate);
    }


    public Parallel(int threadCount, Quality<T> delegate)
    {
        mThreadCount = threadCount;
        mDelegate = delegate;
    }


    @Override
    public Assessment assessmentOf(T candidate)
    {
        ExecutorService executor = Executors.newFixedThreadPool(mThreadCount);
        List<Assessment> results = Collections.synchronizedList(new ArrayList<>(mThreadCount));
        new ForEach<>(new First<>(mThreadCount, new Sequence<>(0, i -> i + 1)))
            .process(
                i -> {
                    results.add(i, new Fail(new TextDescription("missing result " + i)));
                    executor.execute(() -> {
                        try
                        {
                            results.set(i, new FailPrepended(new TextDescription("#" + i + " in thread " + Thread.currentThread().getName()),
                                mDelegate.assessmentOf(candidate)));
                        }
                        catch (Exception e)
                        {
                            results.set(i, new Fail(new Delimited(new TextDescription("#" + i + " in thread " + Thread.currentThread().getName()),
                                new ValueDescription(e))));
                        }
                    });
                }
            );
        executor.shutdown();
        try
        {
            if (!executor.awaitTermination(1, TimeUnit.DAYS))
            {
                return new Fail(new TextDescription("did not finish within one day"));
            }
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
            return new Fail(new TextDescription("interrupted"));
        }
        return new AllPassed(new TextDescription("executions: "), COMMA_NEW_LINE, EMPTY, results);
    }


    @Override
    public Description description()
    {
        return new Delimited(new TextDescription("running " + mThreadCount + " parallel execution, each"), mDelegate.description());
    }
}
