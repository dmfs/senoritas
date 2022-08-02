package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Passes;
import org.saynotobugs.confidence.test.quality.Fails;

import static org.saynotobugs.confidence.Assertion.assertThat;


class LessThanTest
{
    @Test
    void test()
    {
        assertThat(new LessThan<>(10),
            new AllOf<>(
                new Passes<>(9, 8, 7, 9, -1),
                new Fails<>(10, "<10>"),
                new Fails<>(11, "<11>"),
                new Expects("less than <10>")
            ));
    }
}