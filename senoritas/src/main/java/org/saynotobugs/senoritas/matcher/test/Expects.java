package org.saynotobugs.senoritas.matcher.test;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.Having;
import org.saynotobugs.senoritas.matcher.core.MatcherComposition;

import java.util.regex.Pattern;


@StaticFactories("Test")
public final class Expects extends MatcherComposition<Matcher<?>>
{

    public Expects(String expectation)
    {
        this(new DescribesAs(expectation));
    }


    public Expects(Pattern expectation)
    {
        this(new DescribesAs(expectation));
    }


    public Expects(Matcher<? super Description> delegate)
    {
        super(new Having<>(new TextDescription("expects"), new TextDescription("expected"), Matcher::expectation, delegate));
    }
}
