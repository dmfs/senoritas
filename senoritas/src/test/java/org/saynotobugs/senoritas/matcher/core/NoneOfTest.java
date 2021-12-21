package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class NoneOfTest
{
    @Test
    void test()
    {
        assertThat(new NoneOf<>(1, 2, 3),
            new AllOf<>(
                new Matches<>(0, 4, 5, 6),
                new Mismatches<>(1, "was <1>\n  ..."),
                new Mismatches<>(2, "was ...\n  <2>\n  ..."),
                new Mismatches<>(3, "was ...\n  <3>"),
                new Expects("None of <1>\n  and\n  <2>\n  and\n  <3>")));
    }


    @Test
    void testMatchers()
    {
        assertThat(new NoneOf<>(new EqualTo<>(1), new EqualTo<>(2), new EqualTo<>(3)),
            new AllOf<>(
                new Matches<>(0, 4, 5, 6),
                new Mismatches<>(1, "was <1>\n  ..."),
                new Mismatches<>(2, "was ...\n  <2>\n  ..."),
                new Mismatches<>(3, "was ...\n  <3>"),
                new Expects("None of <1>\n  and\n  <2>\n  and\n  <3>")));
    }
}