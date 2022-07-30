package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.DescribesAs;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import java.util.Optional;

import static org.saynotobugs.quality.Assertion.assertThat;


class AbsentTest
{

    @Test
    void test()
    {
        assertThat(new Absent<>(),
            new AllOf<>(
                new Passes<Optional<Integer>>(Optional.empty()),
                new Fails<>(Optional.of(123), new DescribesAs("<present <123>>")),
                new Expects("<empty> optional")
            ));
    }

}