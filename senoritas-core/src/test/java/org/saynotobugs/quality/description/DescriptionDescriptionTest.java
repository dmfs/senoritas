package org.saynotobugs.quality.description;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.DescribesAs;

import static org.saynotobugs.quality.Assertion.assertThat;


class DescriptionDescriptionTest
{

    @Test
    void test()
    {
        assertThat(
            new DescriptionDescription(new TextDescription("abc")),
            new DescribesAs("\n  ----\n  abc\n  ----"));
    }

}