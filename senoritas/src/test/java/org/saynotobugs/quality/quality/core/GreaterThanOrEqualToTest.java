package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


class GreaterThanOrEqualToTest
{
    @Test
    void test()
    {
        assertThat(new GreaterThanOrEqualTo<>(10),
            new AllOf<>(
                new Passes<>(10, 11, 12, 100, 1000),
                new Fails<>(9, "<9>"),
                new Expects("greater than or equal to <10>")
            ));
    }
}