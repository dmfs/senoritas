package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;


@StaticFactories("RxJava3")
public final class IsCancelled<T> extends TestEventComposition<T>
{
    public IsCancelled()
    {
        super(testScheduler -> new org.saynotobugs.senoritas.matcher.rxjava3.internal.IsCancelled<>());
    }
}
