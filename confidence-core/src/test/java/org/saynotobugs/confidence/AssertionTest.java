package org.saynotobugs.confidence;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.Is;
import org.saynotobugs.confidence.quality.Nothing;
import org.saynotobugs.confidence.quality.Throwing;

import static org.saynotobugs.confidence.Assertion.assertThat;


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