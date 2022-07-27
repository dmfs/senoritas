package org.saynotobugs.quality.quality.core;

import org.dmfs.jems2.iterable.Mapped;
import org.dmfs.jems2.iterable.Seq;
import org.dmfs.jems2.optional.First;
import org.dmfs.jems2.single.Backed;
import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Assessment;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.Indented;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.assessment.Fail;


@StaticFactories("Core")
public final class Contains<T> extends QualityComposition<Iterable<? extends T>>
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
                new Fail(new Delimited(new TextDescription("did not contain"), new Indented(delegate.description()))))
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
