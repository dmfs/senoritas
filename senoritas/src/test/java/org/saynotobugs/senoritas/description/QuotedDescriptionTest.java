package org.saynotobugs.senoritas.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class QuotedDescriptionTest
{

    @Test
    void testAllEmpty()
    {
        assertThat(new QuotedDescription("", scribe -> {}),
            new DescribesAs(""));
    }


    @Test
    void testEmptyDelegate()
    {
        assertThat(new QuotedDescription("abc", scribe -> {}),
            new DescribesAs("abcabc"));
    }


    @Test
    void testEmptyQuotes()
    {
        assertThat(new QuotedDescription("", scribe -> scribe.append("123")),
            new DescribesAs("123"));
    }


    @Test
    void testNonEmpty()
    {
        assertThat(new QuotedDescription("abc", scribe -> scribe.append("123")),
            new DescribesAs("abc123abc"));
    }


    @Test
    void testEntryAndExit()
    {
        assertThat(new QuotedDescription("abc", scribe -> scribe.append("123"), "xyz"),
            new DescribesAs("abc123xyz"));
    }
}