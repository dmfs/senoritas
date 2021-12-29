package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import java.util.Optional;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class AbsentTest
{

    @Test
    void test()
    {
        assertThat(new Absent<>(),
            new AllOf<>(
                new Matches<Optional<Integer>>(Optional.empty()),
                new Mismatches<>(Optional.of(123), new DescribesAs("<present <123>>")),
                new Expects("<empty> optional")
            ));
    }

}