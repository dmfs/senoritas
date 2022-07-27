package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Passes;
import org.saynotobugs.quality.quality.test.Fails;

import static org.saynotobugs.quality.Assertion.assertThat;


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