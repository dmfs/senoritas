package org.saynotobugs.senoritas.matcher.rxjava3.adapters;

public interface SubjectAdapter<T>
{
    void onNext(T next);

    void onComplete();

    void onError(Throwable error);
}
