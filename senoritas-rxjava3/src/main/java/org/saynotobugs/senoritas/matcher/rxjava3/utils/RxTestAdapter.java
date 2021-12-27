package org.saynotobugs.senoritas.matcher.rxjava3.utils;

import java.util.Collection;


public interface RxTestAdapter<T>
{
    long completions();

    Collection<T> newValues(int count);

    void awaitNext(int count);

    void ack(int count);

    Iterable<Throwable> errors();

    boolean isCancelled();
}
