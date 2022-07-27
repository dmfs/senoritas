package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Passes;
import org.saynotobugs.quality.quality.test.Fails;

import static org.saynotobugs.quality.Assertion.assertThat;


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