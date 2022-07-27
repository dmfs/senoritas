package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Passes;
import org.saynotobugs.quality.quality.test.Fails;

import static org.saynotobugs.quality.Assertion.assertThat;


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