package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


class NoneOfTest
{
    @Test
    void test()
    {
        assertThat(new NoneOf<>(1, 2, 3),
            new AllOf<>(
                new Passes<>(0, 4, 5, 6),
                new Fails<>(1, "was <1>\n  ..."),
                new Fails<>(2, "was ...\n  <2>\n  ..."),
                new Fails<>(3, "was ...\n  <3>"),
                new Expects("None of <1>\n  and\n  <2>\n  and\n  <3>")));
    }


    @Test
    void testMatchers()
    {
        assertThat(new NoneOf<>(new EqualTo<>(1), new EqualTo<>(2), new EqualTo<>(3)),
            new AllOf<>(
                new Passes<>(0, 4, 5, 6),
                new Fails<>(1, "was <1>\n  ..."),
                new Fails<>(2, "was ...\n  <2>\n  ..."),
                new Fails<>(3, "was ...\n  <3>"),
                new Expects("None of <1>\n  and\n  <2>\n  and\n  <3>")));
    }
}