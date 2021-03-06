package org.saynotobugs.senoritas.matcher.rxjava3.adapters;

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
        if (!mDelegate.hasComplete()) // the subject may have been completed by onSuccess above.
        {
            mDelegate.onComplete();
        }
    }


    @Override
    public void onError(Throwable error)
    {
        mDelegate.onError(error);
    }
}
