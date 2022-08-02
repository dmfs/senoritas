package org.saynotobugs.quality.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.test.quality.DescribesAs;

import static org.saynotobugs.quality.Assertion.assertThat;
import static org.saynotobugs.quality.description.LiteralDescription.*;


class LiteralDescriptionTest
{
    @Test
    void test()
    {
        assertThat(NEW_LINE, new DescribesAs("\n"));
        assertThat(COMMA, new DescribesAs(","));
        assertThat(COMMA_NEW_LINE, new DescribesAs(",\n"));
        assertThat(DQUOTES, new DescribesAs("\""));
        assertThat(EMPTY, new DescribesAs(""));
        assertThat(NULL, new DescribesAs("<null>"));
        assertThat(SPACE, new DescribesAs(" "));
    }
}