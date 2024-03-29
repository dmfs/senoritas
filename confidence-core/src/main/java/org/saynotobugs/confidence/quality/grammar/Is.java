package org.saynotobugs.confidence.quality.grammar;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.Delimited;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.DescribedAs;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.object.EqualTo;


@StaticFactories(value = "Core", packageName = "org.saynotobugs.confidence.quality")
public final class Is<T> extends QualityComposition<T>
{
    public Is(T delegate)
    {
        this(new EqualTo<>(delegate));
    }


    public Is(Quality<T> delegate)
    {
        super(new DescribedAs<>(d -> new Delimited(new TextDescription("was"), d), d -> new Delimited(new TextDescription("is"), d), delegate));
    }
}
