package org.saynotobugs.quality.quality.test;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.quality.Description;
import org.saynotobugs.quality.Quality;
import org.saynotobugs.quality.description.TextDescription;
import org.saynotobugs.quality.quality.core.Having;
import org.saynotobugs.quality.quality.core.QualityComposition;

import java.util.regex.Pattern;


@StaticFactories("Test")
public final class Expects extends QualityComposition<Quality<?>>
{

    public Expects(String expectation)
    {
        this(new DescribesAs(expectation));
    }


    public Expects(Pattern expectation)
    {
        this(new DescribesAs(expectation));
    }


    public Expects(Quality<? super Description> delegate)
    {
        super(new Having<>(new TextDescription("expects"), new TextDescription("expected"), Quality::description, delegate));
    }
}
