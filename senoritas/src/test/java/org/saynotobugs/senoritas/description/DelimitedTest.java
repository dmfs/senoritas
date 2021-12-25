package org.saynotobugs.senoritas.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;

import static org.saynotobugs.senoritas.Assertion.assertThat;


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