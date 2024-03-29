package org.saynotobugs.confidence.quality.composite;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.assessment.PassIf;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.description.ValueDescription;
import org.saynotobugs.confidence.quality.object.EqualTo;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class Not<T> extends QualityComposition<T>
{

    public Not(T delegate)
    {
        this(new EqualTo<>(delegate));
    }


    public Not(Quality<? super T> delegate)
    {
        super(
            actual -> new PassIf(
                !delegate.assessmentOf(actual).isSuccess(),
                new Delimited(new ValueDescription(actual), new TextDescription("("), delegate.description(), new TextDescription(")"))),
            new Delimited(new TextDescription("not ("), delegate.description(), new TextDescription(")")));
    }
}
