package org.saynotobugs.quality.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.DescribesAs;

import static org.saynotobugs.quality.Assertion.assertThat;
import static org.saynotobugs.quality.description.LiteralDescription.NEW_LINE;


class IndentedTest
{
    @Test
    void test()
    {
        assertThat(new Indented(NEW_LINE), new DescribesAs("\n  "));
        assertThat(new Indented(new Composite(NEW_LINE, NEW_LINE)), new DescribesAs("\n  \n  "));
    }
}