package org.saynotobugs.confidence.test.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.Having;
import org.saynotobugs.confidence.quality.composite.QualityComposition;

import java.util.regex.Pattern;


@StaticFactories("Test")
public final class Description extends QualityComposition<Quality<?>>
{

    public Description(String expectation)
    {
        this(new DescribesAs(expectation));
    }


    public Description(Pattern expectation)
    {
        this(new DescribesAs(expectation));
    }


    public Description(Quality<? super org.saynotobugs.confidence.Description> delegate)
    {
        super(new Having<>(new TextDescription("description"), new TextDescription("description"), Quality::description, delegate));
    }
}
