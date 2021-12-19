package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class LessThanTest
{
    @Test
    void test()
    {
        assertThat(new LessThan<>(10),
            new AllOf<>(
                new Matches<>(9, 8, 7, 9, -1),
                new Mismatches<>(10, "<10>"),
                new Mismatches<>(11, "<11>"),
                new Expects("less than <10>")
            ));
    }
}