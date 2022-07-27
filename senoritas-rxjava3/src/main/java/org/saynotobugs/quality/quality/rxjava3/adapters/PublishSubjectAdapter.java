package org.saynotobugs.quality.quality.rxjava3.adapters;

import io.reactivex.rxjava3.subjects.PublishSubject;


public final class PublishSubjectAdapter<T> implements RxSubjectAdapter<T>
{
    private final PublishSubject<T> mDelegate;


    public PublishSubjectAdapter(PublishSubject<T> delegate)
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
