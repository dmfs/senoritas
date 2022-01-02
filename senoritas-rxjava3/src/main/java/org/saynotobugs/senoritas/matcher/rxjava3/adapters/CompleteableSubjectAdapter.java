package org.saynotobugs.senoritas.matcher.rxjava3.adapters;

import io.reactivex.rxjava3.subjects.CompletableSubject;


public final class CompleteableSubjectAdapter<T> implements SubjectAdapter<T>
{
    private final CompletableSubject mDelegate;


    public CompleteableSubjectAdapter(CompletableSubject delegate)
    {
        mDelegate = delegate;
    }


    @Override
    public void onNext(T next)
    {
        // Completables don't take values.
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
