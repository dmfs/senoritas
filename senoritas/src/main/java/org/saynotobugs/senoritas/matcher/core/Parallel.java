package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.generatable.Sequence;
import org.dmfs.jems2.iterable.First;
import org.dmfs.jems2.procedure.ForEach;
import org.saynotobugs.senoritas.Verdict;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.verdict.AllPass;
import org.saynotobugs.senoritas.verdict.Fail;
import org.saynotobugs.senoritas.verdict.Updated;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.TextDescription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public final class Parallel<T> implements Matcher<T>
{
    private final int mThreadCount;
    private final Matcher<T> mDelegate;


    public Parallel(Matcher<T> delegate)
    {
        this(1000, delegate);
    }


    public Parallel(int threadCount, Matcher<T> delegate)
    {
        mThreadCount = threadCount;
        mDelegate = delegate;
    }


    @Override
    public Verdict match(T actual)
    {
        ExecutorService executor = Executors.newFixedThreadPool(mThreadCount);
        List<Verdict> results = Collections.synchronizedList(new ArrayList<>(mThreadCount));
        new ForEach<>(new First<>(mThreadCount, new Sequence<>(0, i -> i + 1)))
            .process(
                i -> executor.execute(() -> {
                    try
                    {
                        results.add(new Updated(orig -> new Composite(new TextDescription("#" + i + " in thread " + Thread.currentThread().getName()), orig),
                            mDelegate.match(actual)));
                    }
                    catch (Exception e)
                    {
                        results.add(new Fail(new TextDescription(e.getMessage())));
                    }
                })
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
        return new AllPass("Executions:", ",", "", results);
    }


    @Override
    public Description expectation()
    {
        return new Composite(new TextDescription("running " + mThreadCount + " parallel execution, each"), mDelegate.expectation());
    }
}
