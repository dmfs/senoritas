package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class GreaterThanOrEqualToTest
{
    @Test
    void test()
    {
        assertThat(new GreaterThanOrEqualTo<>(10),
            new AllOf<>(
                new Matches<>(10,11,12,100,1000),
                new Mismatches<>(9, "<9>"),
                new Expects("greater than or equal to <10>")
            ));
    }
}