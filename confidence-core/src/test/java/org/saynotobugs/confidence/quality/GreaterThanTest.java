package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Passes;
import org.saynotobugs.confidence.test.quality.Fails;

import static org.saynotobugs.confidence.Assertion.assertThat;


class GreaterThanTest
{
    @Test
    void test()
    {
        assertThat(new GreaterThan<>(10),
            new AllOf<>(
                new Passes<>(11, 12, 100, 1000),
                new Fails<>(10, "<10>"),
                new Fails<>(9, "<9>"),
                new Expects("greater than <10>")
            ));
    }
}