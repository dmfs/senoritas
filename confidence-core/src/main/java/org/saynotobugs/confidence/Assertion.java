package org.saynotobugs.confidence;

import org.saynotobugs.confidence.scribe.StringBuilderScribe;


public final class Assertion
{
    public static <T> void assertThat(T candidate, Quality<? super T> quality) throws AssertionError
    {
        Assessment assessment = quality.assessmentOf(candidate);
        if (!assessment.isSuccess())
        {
            Scribe ex = new StringBuilderScribe("  ");
            quality.description().describeTo(ex);
            Scribe diff = new StringBuilderScribe("  ");
            assessment.description().describeTo(diff);
            throw new AssertionError(
                "Expected:" + System.lineSeparator() + System.lineSeparator()
                    + ex + System.lineSeparator() + System.lineSeparator() +
                    "Actual:   " + System.lineSeparator() + System.lineSeparator() + diff + System.lineSeparator());
        }
    }
}
