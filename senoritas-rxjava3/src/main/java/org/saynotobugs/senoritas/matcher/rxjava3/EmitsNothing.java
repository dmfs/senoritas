package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;


@StaticFactories("RxJava3")
public final class EmitsNothing<T> extends TestEventComposition<T>
{
    public EmitsNothing()
    {
        super(testScheduler -> new org.saynotobugs.senoritas.matcher.rxjava3.internal.EmitsNothing<>());
    }
}
