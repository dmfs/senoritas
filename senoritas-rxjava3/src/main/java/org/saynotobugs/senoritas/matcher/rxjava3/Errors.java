package org.saynotobugs.senoritas.matcher.rxjava3;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.core.InstanceOf;


@StaticFactories("RxJava3")
public final class Errors<T> extends TestEventComposition<T>
{
    public Errors(Class<? extends Throwable> error)
    {
        this(new InstanceOf<>(error));
    }


    public Errors(Matcher<? super Throwable> errorMatcher)
    {
        super(testScheduler -> new org.saynotobugs.senoritas.matcher.rxjava3.internal.Errors<>(errorMatcher));
    }
}
