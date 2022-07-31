package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.LessThanOrEqualTo;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


class LessThanOrEqualToTest
{
    @Test
    void test()
    {
        assertThat(new LessThanOrEqualTo<>(10),
            new AllOf<>(
                new Passes<>(10, 9, 8, 7, 9, -1),
                new Fails<>(11, "<11>"),
                new Expects("less than or equal to <10>")
            ));
    }
}