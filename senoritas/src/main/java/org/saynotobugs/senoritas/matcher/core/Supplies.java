package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.Delimited;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.verdict.MismatchPrepended;

import java.util.function.Supplier;


@StaticFactories("Core")
public final class Supplies<T> extends MatcherComposition<Supplier<T>>
{
    /**
     * Creates a {@link Matcher} that passes if the {@link Supplier} under test supplies a value equal to the given value.
     */
    public Supplies(T delegate)
    {
        this(new EqualTo<>(delegate));
    }


    /**
     * Creates a {@link Matcher} that passes if the {@link Supplier} under test supplies a value that matches the given {@link Matcher}.
     */
    public Supplies(Matcher<? super T> delegate)
    {
        super(actual -> new MismatchPrepended(new TextDescription("supplied value"), delegate.match(actual.get())),
            new Delimited(new TextDescription("supplies value"), delegate.expectation()));
    }
}
