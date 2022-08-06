package org.saynotobugs.confidence.quality.iterable;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.single.Backed;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Assessment;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.Fail;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.Indented;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.EqualTo;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class Contains<T> extends QualityComposition<Iterable<T>>
{

    /**
     * Creates a {@link Quality} that checks if the {@link Iterable} under test contains at least one element that equals the given value.
     * <p>
     * Example
     * <pre>
     * assertThat(asList("foo", "bar", "baz"), contains("bar"));
     * </pre>
     */
    public Contains(T value)
    {
        this(new EqualTo<>(value));
    }


    /**
     * Creates a {@link Quality} that checks if the {@link Iterable} under test contains at least one element that matches the given {@link Quality}.
     * <p>
     * Example
     * <pre>
     * assertThat(asList("foo", "bar", "baz"), contains(equalTo("bar")));
     * </pre>
     */
    public Contains(Quality<? super T> delegate)
    {
        super(actual -> new Backed<>(
                new First<>(Assessment::isSuccess, new Mapped<>(delegate::assessmentOf, actual)),
                new Fail(new Delimited(new ValueDescription(actual), new TextDescription("did not contain"), new Indented(delegate.description()))))
                .value(),
            new Delimited(new TextDescription("contains"), new Indented(delegate.description())));
    }


    /**
     * Creates a {@link Quality} that, for each given values, checks if the {@link Iterable} under test contains at least one element that equals that value.
     * <p>
     * Example
     * <pre>
     * assertThat(asList("foo", "bar", "baz"), contains("foo", "bar"));
     * </pre>
     */
    @SafeVarargs
    public Contains(T... values)
    {
        this(new Mapped<>(EqualTo::new, new Seq<>(values)));
    }


    @SafeVarargs
    public Contains(Quality<? super T>... delegates)
    {
        this(new Seq<>(delegates));
    }


    public Contains(Iterable<? extends Quality<? super T>> delegates)
    {
        super(new AllOf<>(new Mapped<>(Contains::new, delegates)));
    }

}
