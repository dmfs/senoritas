package org.saynotobugs.senoritas.matcher.rxjava3.adapters;

import io.reactivex.rxjava3.processors.PublishProcessor;


public final class FlowableSubjectAdapter<T> implements SubjectAdapter<T>
{
    private final PublishProcessor<T> mDelegate;


    public FlowableSubjectAdapter(PublishProcessor<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public void onNext(T next)
    {
        mDelegate.onNext(next);
    }


    @Override
    public void onComplete()
    {
        mDelegate.onComplete();
    }


    @Override
    public void onError(Throwable error)
    {
        mDelegate.onError(error);
    }
}
