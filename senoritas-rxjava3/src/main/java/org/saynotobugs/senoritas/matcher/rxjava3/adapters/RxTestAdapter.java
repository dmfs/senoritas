package org.saynotobugs.senoritas.matcher.rxjava3.adapters;

import java.util.Collection;

import io.reactivex.rxjava3.observers.TestObserver;
import io.reactivex.rxjava3.subscribers.TestSubscriber;


/**
 * A generalization of {@link TestObserver} and {@link TestSubscriber}.
 */
public interface RxTestAdapter<T>
{
    /**
     * Waits (at most 5 seconds) for at least count values to be emitted.
     * This methods returns without an error even if no {@code count} have been emitted.
     */
    void awaitNext(int count);

    /**
     * Returns the next count values that have not been acknowledged so far.
     * The result will have fewer elements if no {@code count} elements have been emitted.
     */
    Collection<T> newValues(int count);

    void ack(int count);

    long completions();

    Iterable<Throwable> errors();

    boolean isCancelled();
}
