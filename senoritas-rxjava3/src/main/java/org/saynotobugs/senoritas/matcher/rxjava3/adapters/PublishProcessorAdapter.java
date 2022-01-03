package org.saynotobugs.senoritas.matcher.rxjava3.adapters;

import io.reactivex.rxjava3.processors.PublishProcessor;


/**
 * An {@link RxSubjectAdapter} to a {@link PublishProcessor}. It delegates all methods to the respective {@link PublishProcessor} methods.
 */
public final class PublishProcessorAdapter<T> implements RxSubjectAdapter<T>
{
    private final PublishProcessor<T> mDelegate;


    public PublishProcessorAdapter(PublishProcessor<T> delegate)
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
