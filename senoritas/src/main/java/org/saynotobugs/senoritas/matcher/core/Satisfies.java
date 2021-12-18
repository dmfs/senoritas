package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.Predicate;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.verdict.PassIf;


@StaticFactories("Core")
public final class Satisfies<T> extends MatcherComposition<T>
{
    /**
     * Creates a {@link Matcher} that matches any value that satisfies the given {@link Predicate}.
     * <p>
     * Example
     * <pre>
     * assertThat("",
     *     satisfies(
     *         String::isEmpty,
     *         s-&gt;new Delimited(new ValueDescription(s), new TextDescription("was not empty")),
     *         new TextDescription("is empty")));
     * </pre>
     */
    public Satisfies(
        Predicate<? super T> predicate,
        Function<? super T, ? extends Description> mismatchDescriptionFunction,
        Description matchDescription)
    {
        super(actual -> new PassIf(predicate.satisfiedBy(actual), () -> mismatchDescriptionFunction.value(actual)),
            matchDescription);
    }
}
