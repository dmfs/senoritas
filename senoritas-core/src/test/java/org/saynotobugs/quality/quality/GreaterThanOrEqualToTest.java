package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.GreaterThanOrEqualTo;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

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