package org.saynotobugs.confidence.rxjava3.adapters;

import org.saynotobugs.confidence.rxjava3.RxSubjectAdapter;

import io.reactivex.rxjava3.subjects.MaybeSubject;


public final class MaybeSubjectAdapter<T> implements RxSubjectAdapter<T>
{
    private final MaybeSubject<T> mDelegate;


    public MaybeSubjectAdapter(MaybeSubject<T> delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public void onNext(T next)
    {
        mDelegate.onSuccess(next);
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
