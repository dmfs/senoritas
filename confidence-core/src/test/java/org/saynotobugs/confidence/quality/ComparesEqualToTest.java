package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class ComparesEqualToTest
{
    @Test
    void test()
    {
        assertThat(new ComparesEqualTo<>(3),
            new AllOf<>(
                new Passes<>(3),
                new Fails<>(4, "<4>"),
                new Expects("compares equal to <3>")));
    }
}