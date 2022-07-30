package org.saynotobugs.senoritas.mockito4;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.core.AllOf;
import org.saynotobugs.quality.quality.core.EqualTo;
import org.saynotobugs.quality.quality.core.HasToString;
import org.saynotobugs.quality.quality.core.Not;

import static org.saynotobugs.quality.Assertion.assertThat;


class ArgThatTest
{

    @Test
    void test()
    {
        assertThat(new ArgThat<>(new EqualTo<>("123")),
            new AllOf<>(
                new Matches<>("123"),
                new Not<>(new Matches<>("1234")),
                new HasToString("ArgThat \"123\"")
            ));
    }

}