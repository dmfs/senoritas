package org.saynotobugs.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.core.Is;
import org.saynotobugs.quality.quality.core.Nothing;
import org.saynotobugs.quality.quality.core.Throwing;

import static org.saynotobugs.quality.Assertion.assertThat;


class AssertionTest
{

    @Test
    void test()
    {
        assertThat(() -> {
                assertThat("123", new Nothing());
                // I guess throwing needs a dedicated interface to test
            },
            new Is<>(new Throwing(AssertionError.class)));
    }

}