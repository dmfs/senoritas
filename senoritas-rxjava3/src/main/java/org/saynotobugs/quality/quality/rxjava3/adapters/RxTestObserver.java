package org.saynotobugs.quality.quality.rxjava3.adapters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.rxjava3.observers.TestObserver;


public final class RxTestObserver<T> extends TestObserver<T> implements RxTestAdapter<T>
{
    private final AtomicInteger mAcknowledged = new AtomicInteger(0);


    @Override
    public long completions()
    {
        return completions;
    }


    @Override
    public Collection<T> newValues(int count)
    {
        int valueCount = Math.min(count, values.size() - mAcknowledged.get());
        List<T> newValues = new ArrayList<>(valueCount);
        newValues.addAll(values.subList(mAcknowledged.get(), mAcknowledged.get() + valueCount));
        return newValues;
    }


    @Override
    public void awaitNext(int count)
    {
        awaitCount(mAcknowledged.get() + count);
    }


    @Override
    public void ack(int count)
    {
        mAcknowledged.addAndGet(count);
    }


    @Override
    public Iterable<Throwable> errors()
    {
        return new ArrayList<>(errors);
    }


    @Override
    public boolean isCancelled()
    {
        return super.isDisposed();
    }
}
