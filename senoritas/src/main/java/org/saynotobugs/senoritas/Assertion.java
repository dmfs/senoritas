package org.saynotobugs.senoritas;

import org.saynotobugs.senoritas.scribe.StringBuilderScribe;


public final class Assertion
{
    public static <T> void assertThat(T candidate, Matcher<? super T> matcher) throws AssertionError
    {
        Verdict verdict = matcher.match(candidate);
        if (!verdict.isSuccess())
        {
            Scribe ex = new StringBuilderScribe("  ");
            matcher.expectation().describeTo(ex);
            Scribe diff = new StringBuilderScribe("  ");
            verdict.description().describeTo(diff);
            throw new AssertionError(
                "Expected:" + System.lineSeparator() + System.lineSeparator()
                    + ex + System.lineSeparator() + System.lineSeparator() +
                    "Actual:   " + System.lineSeparator() + System.lineSeparator() + diff + System.lineSeparator());
        }
    }
}
