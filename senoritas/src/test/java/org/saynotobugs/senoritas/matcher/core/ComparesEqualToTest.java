package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class ComparesEqualToTest
{
    @Test
    void test()
    {
        assertThat(new ComparesEqualTo<>(3),
            new AllOf<>(
                new Matches<>(3),
                new Mismatches<>(4, "<4>"),
                new Expects("compares equal to <3>")));
    }
}