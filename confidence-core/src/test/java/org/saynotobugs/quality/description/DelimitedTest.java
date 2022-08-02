package org.saynotobugs.quality.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.test.quality.DescribesAs;

import static org.saynotobugs.quality.Assertion.assertThat;


class DelimitedTest
{
    @Test
    void testDefaultDelimiter()
    {
        assertThat(new Delimited(), new DescribesAs(""));
        assertThat(new Delimited(new TextDescription("a")), new DescribesAs("a"));
        assertThat(new Delimited(new TextDescription("a"), new TextDescription("b"), new TextDescription("c")), new DescribesAs("a b c"));
    }


    @Test
    void testCustomDelimiter()
    {
        assertThat(new Delimited("--"), new DescribesAs(""));
        assertThat(new Delimited("--", new TextDescription("a")), new DescribesAs("a"));
        assertThat(new Delimited("--", new TextDescription("a"), new TextDescription("b"), new TextDescription("c")), new DescribesAs("a--b--c"));
    }

}