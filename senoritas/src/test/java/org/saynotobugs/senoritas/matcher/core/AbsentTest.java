package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.matcher.DescribesAs;
import org.saynotobugs.senoritas.matcher.matcher.Expects;
import org.saynotobugs.senoritas.matcher.matcher.Matches;
import org.saynotobugs.senoritas.matcher.matcher.Mismatches;

import java.util.Optional;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class AbsentTest
{

    @Test
    void test()
    {
        assertThat(new Absent<>(),
            new AllOf<>(
                new Matches<>(Optional.empty()),
                new Mismatches<>(Optional.of(123), new DescribesAs("<present <123>>")),
                new Expects("<empty> optional")
            ));
    }

}