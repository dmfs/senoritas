package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;


@StaticFactories("Core")
public final class HasLength extends MatcherComposition<CharSequence>
{
    /**
     * Creates a {@link Matcher} that matches if length of the {@link CharSequence} under test is equal to the given value.
     * <pre>
     *     assertThat("123", hasLength(3));
     * </pre>
     */
    public HasLength(int size)
    {
        this(new EqualTo<>(size));
    }


    /**
     * Creates a {@link Matcher} that matches if the length {@link CharSequence} under test matches the given {@link Matcher}.
     * <pre>
     *     assertThat("123", hasLength(lessThan(4)));
     * </pre>
     */
    public HasLength(Matcher<? super Integer> delegate)
    {
        super(new Having<>(new TextDescription("has length"), new TextDescription("had length"), CharSequence::length, delegate));
    }
}
