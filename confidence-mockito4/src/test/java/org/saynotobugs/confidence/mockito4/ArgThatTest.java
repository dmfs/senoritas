package org.saynotobugs.confidence.mockito4;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.AllOf;
import org.saynotobugs.confidence.quality.EqualTo;
import org.saynotobugs.confidence.quality.HasToString;
import org.saynotobugs.confidence.quality.Not;

import static org.saynotobugs.confidence.Assertion.assertThat;


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