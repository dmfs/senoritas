package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.LessThan;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Passes;
import org.saynotobugs.quality.test.quality.Fails;

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