package org.saynotobugs.senoritas.matcher.rxjava3.adapters;

import io.reactivex.rxjava3.subjects.SingleSubject;


public final class SingleSubjectAdapter<T> implements SubjectAdapter<T>
{
    private final SingleSubject<T> mDelegate;


    public SingleSubjectAdapter(SingleSubject<T> delegate)
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
        // Singles can't be completed without value.
    }


    @Override
    public void onError(Throwable error)
    {
        mDelegate.onError(error);
    }
}
