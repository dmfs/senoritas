package org.saynotobugs.senoritas.matcher.matcher;

import org.saynotobugs.senoritas.Description;
import org.saynotobugs.senoritas.Matcher;
import org.saynotobugs.senoritas.matcher.core.DelegatingMatcher;
import org.saynotobugs.senoritas.matcher.core.Having;


public final class Expects extends DelegatingMatcher<Matcher<?>>
{

    public Expects(String expectation)
    {
        this(new DescribesAs(expectation));
    }


    public Expects(Matcher<? super Description> delegate)
    {
        super(new Having<>("expectation", Matcher::expectation, delegate));
    }
}
