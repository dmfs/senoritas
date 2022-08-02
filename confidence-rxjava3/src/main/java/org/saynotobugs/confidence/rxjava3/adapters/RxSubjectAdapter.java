package org.saynotobugs.confidence.rxjava3.adapters;

import org.reactivestreams.Subscriber;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;


/**
 * A generalization of the interfaces of a {@link Subscriber}, a {@link Observer}, a {@link SingleObserver}, a {@link MaybeObserver} and
 * a {@link CompletableObserver} without the respective onSubscribe methods.
 * <p>
 * This allows us to use the same tools to test all of them.
 */
public interface RxSubjectAdapter<T>
{
    void onNext(T next);

    void onComplete();

    void onError(Throwable error);
}
