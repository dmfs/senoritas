package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


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