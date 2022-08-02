package org.saynotobugs.confidence.rxjava3.adapters;

import org.saynotobugs.confidence.rxjava3.RxSubjectAdapter;

import io.reactivex.rxjava3.subjects.SingleSubject;


/**
 * An {@link RxSubjectAdapter} to a {@link SingleSubject}. Calls to {@link #onNext(Object)} are delegated to {@link SingleSubject#onSuccess(Object)},
 * which automatically "completes" the subject. On the other hand, {@link SingleSubject}s can not complete without a value, so calls to
 * {@link #onComplete()} throw an {@link UnsupportedOperationException}.
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
        throw new UnsupportedOperationException("SingleSubjectAdapter.onComplete() called, but Singles require a value");
    }


    @Override
    public void onError(Throwable error)
    {
        mDelegate.onError(error);
    }
}
