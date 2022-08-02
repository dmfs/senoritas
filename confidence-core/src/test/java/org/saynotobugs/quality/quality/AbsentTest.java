package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.Absent;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.test.quality.DescribesAs;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

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