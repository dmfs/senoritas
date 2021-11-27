package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.matcher.DescribesAs;
import org.saynotobugs.senoritas.matcher.matcher.Matches;
import org.saynotobugs.senoritas.matcher.matcher.Mismatches;

import java.util.Optional;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class PresentTest
{

    @Test
    void test()
    {
        assertThat(new Present<>(123),
            new AllOf<>(
                new Matches<>(Optional.of(123)),
                new Mismatches<>(Optional.of(1234), new DescribesAs("was present <1234>")),
                new Mismatches<Optional<Integer>>(Optional.empty(), new DescribesAs("was absent"))
            ));
    }

}