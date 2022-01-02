package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.matcher.core.AllOf;
import org.saynotobugs.senoritas.matcher.rxjava3.internal.EmitsNothing;


@StaticFactories("RxJava3")
public final class Completes<T> extends TestEventComposition<T>
{
    public Completes()
    {
        super(testScheduler ->
            new AllOf<>(
                new org.saynotobugs.senoritas.matcher.rxjava3.internal.IsComplete(),
                new EmitsNothing<>()));
    }
}
