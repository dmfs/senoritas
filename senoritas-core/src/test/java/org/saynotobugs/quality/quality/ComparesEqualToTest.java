package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.ComparesEqualTo;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Passes;
import org.saynotobugs.quality.test.quality.Fails;

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