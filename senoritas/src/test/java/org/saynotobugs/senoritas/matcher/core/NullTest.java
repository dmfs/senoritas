package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class NullTest
{
    @Test
    void test()
    {
        assertThat(new Null(),
            new AllOf<>(
                new Matches<>(new Object[] { null }),
                new Mismatches<>(123, "<123>"),
                new Expects("<null>")));
    }
}