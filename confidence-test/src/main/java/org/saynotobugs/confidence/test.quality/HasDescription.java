package org.saynotobugs.confidence.test.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.Has;
import org.saynotobugs.confidence.quality.composite.QualityComposition;

import java.util.regex.Pattern;


@StaticFactories("Test")
public final class HasDescription extends QualityComposition<Quality<?>>
{

    public HasDescription(String expectation)
    {
        this(new DescribesAs(expectation));
    }


    public HasDescription(Pattern expectation)
    {
        this(new DescribesAs(expectation));
    }


    public HasDescription(Quality<? super org.saynotobugs.confidence.Description> delegate)
    {
        super(new Has<>(new TextDescription("description"), new TextDescription("description"), Quality::description, delegate));
    }
}
