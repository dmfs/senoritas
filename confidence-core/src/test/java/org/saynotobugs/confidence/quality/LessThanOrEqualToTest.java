package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


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