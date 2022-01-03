package org.saynotobugs.senoritas.matcher.rxjava3.adapters;

import io.reactivex.rxjava3.subjects.SingleSubject;


/**
 * An {@link RxSubjectAdapter} to a {@link SingleSubject}. Calls to {@link #onNext(T)} are delegated to {@link SingleSubject#onSuccess(T)}, which
 * automatically "completes" the subject. On the other hand, {@link SingleSubject}s can not complete without a value, so calls to
 * {@link #onComplete()} are ignored by this adapter.
 */
public final class SingleSubjectAdapter<T> implements RxSubjectAdapter<T>
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
