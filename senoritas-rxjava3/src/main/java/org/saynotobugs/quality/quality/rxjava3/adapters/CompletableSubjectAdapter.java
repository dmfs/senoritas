package org.saynotobugs.quality.quality.rxjava3.adapters;

import io.reactivex.rxjava3.subjects.CompletableSubject;


/**
 * An {@link RxSubjectAdapter} to a {@link CompletableSubject}. {@link CompletableSubject}s don't have values,
 * so calls to {@link #onNext(Object)} throw an {@link UnsupportedOperationException}.
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
        throw new UnsupportedOperationException(
            String.format("CompletableSubjectAdapter.onNext() called with %s, but Completables don't take any values.", next));
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
