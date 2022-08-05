package org.saynotobugs.confidence.test.quality;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.confidence.Description;
import org.saynotobugs.confidence.Quality;
import org.saynotobugs.confidence.description.TextDescription;
import org.saynotobugs.confidence.quality.composite.QualityComposition;
import org.saynotobugs.confidence.quality.composite.Having;

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


    public HasDescription(Quality<? super Description> delegate)
    {
        super(new Having<>(new TextDescription("expects"), new TextDescription("expected"), Quality::description, delegate));
    }
}
