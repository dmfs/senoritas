package org.saynotobugs.senoritas;

import org.saynotobugs.senoritas.scribe.StringBuilderScribe;
import org.hamcrest.Matcher;

import static org.dmfs.jems2.hamcrest.matchers.LambdaMatcher.having;
import static org.hamcrest.Matchers.is;


public final class Hamcrest
{
    public static Matcher<Description> describesAs(String result)
    {
        return having("description", d -> {
            Scribe s = new StringBuilderScribe("  ");
            d.describeTo(s);
            return s.toString();
        }, is(result));
    }
}
