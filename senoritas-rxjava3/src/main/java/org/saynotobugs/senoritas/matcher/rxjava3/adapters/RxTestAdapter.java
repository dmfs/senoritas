package org.saynotobugs.senoritas.matcher.rxjava3.adapters;

import java.util.Collection;

import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.subscribers.TestSubscriber;


/**
 * A generalization of {@link TestObserver} and {@link TestSubscriber}.
 */
public interface RxTestAdapter<T>
{
    long completions();

    Collection<T> newValues(int count);

    void awaitNext(int count);

    void ack(int count);

    Iterable<Throwable> errors();

    boolean isCancelled();
}
