package org.saynotobugs.senoritas.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;

import static org.saynotobugs.senoritas.Assertion.assertThat;
import static org.saynotobugs.senoritas.description.LiteralDescription.NEW_LINE;


class IndentedTest
{
    @Test
    void test()
    {
        assertThat(new Indented(NEW_LINE), new DescribesAs("\n  "));
        assertThat(new Indented(new Composite(NEW_LINE, NEW_LINE)), new DescribesAs("\n  \n  "));
    }
}