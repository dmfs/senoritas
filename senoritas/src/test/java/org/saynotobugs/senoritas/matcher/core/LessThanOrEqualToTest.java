package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class LessThanOrEqualToTest
{
    @Test
    void test()
    {
        assertThat(new LessThanOrEqualTo<>(10),
            new AllOf<>(
                new Matches<>(10, 9, 8, 7, 9, -1),
                new Mismatches<>(11, "<11>"),
                new Expects("less than or equal to <10>")
            ));
    }
}