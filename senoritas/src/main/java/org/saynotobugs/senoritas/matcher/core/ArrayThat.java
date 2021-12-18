package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.utils.ArrayIterable;


@StaticFactories("Core")
public final class ArrayThat extends MatcherComposition<Object>
{
    /**
     * Creates a {@link Matcher} that matches any array that, when iterated, matches the given {@link Iterable} {@link Matcher}.
     */
    @SuppressWarnings("unchecked")
    public <T> ArrayThat(Matcher<? super Iterable<? extends T>> delegate)
    {
        super(
            new Guarded<>(
                new Satisfies<>(a -> a.getClass().isArray(), ignored -> new TextDescription("not an array"), new TextDescription("an array")),
                new Having<>(new TextDescription("array that"), new TextDescription("array that"), a -> (Iterable<T>) new ArrayIterable(a), delegate)));
    }
}
