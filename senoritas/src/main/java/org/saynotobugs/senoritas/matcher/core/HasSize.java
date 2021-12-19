package org.saynotobugs.senoritas.matcher.core;

import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;

import java.util.Collection;


public final class HasSize extends MatcherComposition<Collection<?>>
{
    /**
     * Creates a {@link Matcher} that matches if size of the {@link Collection} under test is equal to the given value.
     * <pre>
     *     assertThat(asList(1,2,3), hasSize(3));
     * </pre>
     */
    public HasSize(int size)
    {
        this(new EqualTo<>(size));
    }


    /**
     * Creates a {@link Matcher} that matches if the size {@link Collection} under test matches the given {@link Matcher}.
     * <pre>
     *     assertThat(asList(1,2,3), hasSize(lessThan(4)));
     * </pre>
     */
    public HasSize(Matcher<? super Integer> delegate)
    {
        super(new Having<>(new TextDescription("has size"), new TextDescription("had size"), Collection::size, delegate));
    }
}
