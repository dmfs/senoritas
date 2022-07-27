package org.saynotobugs.quality.quality.core;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.Delimited;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.description.ValueDescription;
import org.saynotobugs.quality.assessment.PassIf;


@StaticFactories("Core")
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
