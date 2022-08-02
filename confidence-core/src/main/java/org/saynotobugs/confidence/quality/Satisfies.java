package org.saynotobugs.confidence.quality;

import org.dmfs.jems2.Function;
import org.dmfs.jems2.Predicate;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.PassIf;
import org.saynotobugs.confidence.description.ValueDescription;


@StaticFactories("Core")
public final class Satisfies<T> extends QualityComposition<T>
{
    public Satisfies(
        Predicate<? super T> predicate,
        Description matchDescription)
    {
        super(actual -> new PassIf(predicate.satisfiedBy(actual), () -> new ValueDescription(actual)),
            matchDescription);
    }


    /**
     * Creates a {@link Quality} that matches any value that satisfies the given {@link Predicate}.
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
