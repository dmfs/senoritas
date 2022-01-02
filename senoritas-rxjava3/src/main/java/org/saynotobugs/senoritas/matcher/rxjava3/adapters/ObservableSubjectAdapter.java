package org.saynotobugs.senoritas.matcher.rxjava3.adapters;

import io.reactivex.rxjava3.subjects.PublishSubject;


public final class ObservableSubjectAdapter<T> implements SubjectAdapter<T>
{
    private final PublishSubject<T> mDelegate;


    public ObservableSubjectAdapter(PublishSubject<T> delegate)
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
