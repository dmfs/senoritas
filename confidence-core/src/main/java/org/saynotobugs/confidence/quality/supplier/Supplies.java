package org.saynotobugs.confidence.quality.supplier;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.FailPrepended;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.EqualTo;

import java.util.function.Supplier;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class Supplies<T> extends QualityComposition<Supplier<T>>
{
    /**
     * Creates a {@link Quality} that passes if the {@link Supplier} under test supplies a value equal to the given value.
     */
    public Supplies(T delegate)
    {
        this(new EqualTo<>(delegate));
    }


    /**
     * Creates a {@link Quality} that passes if the {@link Supplier} under test supplies a value that matches the given {@link Quality}.
     */
    public Supplies(Quality<? super T> delegate)
    {
        super(actual -> new FailPrepended(new TextDescription("supplied"), delegate.assessmentOf(actual.get())),
            new Delimited(new TextDescription("supplies"), delegate.description()));
    }
}
