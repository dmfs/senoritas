package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.DescribesAs;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import java.util.Optional;

import static org.saynotobugs.confidence.Assertion.assertThat;


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