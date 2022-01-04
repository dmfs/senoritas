package org.saynotobugs.senoritas.matcher.rxjava3.adapters;

import io.reactivex.rxjava3.subjects.CompletableSubject;


/**
 * An {@link RxSubjectAdapter} to a {@link CompletableSubject}. {@link CompletableSubject}s don't have values,
 * so calls to {@link #onNext(Object)} are ignored.
 */
public final class CompletableSubjectAdapter<T> implements RxSubjectAdapter<T>
{
    private final CompletableSubject mDelegate;


    public CompletableSubjectAdapter(CompletableSubject delegate)
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
