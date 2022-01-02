package org.saynotobugs.senoritas.matcher.rxjava3.internal;

import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.core.Having;
import org.saynotobugs.senoritas.matcher.core.InstanceOf;
import org.saynotobugs.senoritas.matcher.core.Iterates;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;
import org.saynotobugs.senoritas.matcher.rxjava3.adapters.RxTestAdapter;


public final class Errors<T> extends MatcherComposition<RxTestAdapter<T>>
{
    public Errors(Class<Throwable> error)
    {
        this(new InstanceOf<>(error));
    }


    public Errors(Matcher<? super Throwable> errorMatcher)
    {
        super(new Having<>("error", RxTestAdapter::errors, new Iterates<>(errorMatcher)));
    }
}
