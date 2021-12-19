package org.saynotobugs.senoritas.matcher.test;

import org.dmfs.srcless.annotations.staticfactory.StaticFactories;
import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.MatcherComposition;
import org.saynotobugs.senoritas.matcher.core.Having;

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
        super(new Having<>("expectation", Matcher::expectation, delegate));
    }
}
