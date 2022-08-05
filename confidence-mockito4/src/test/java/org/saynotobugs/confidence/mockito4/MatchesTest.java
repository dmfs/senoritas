package org.saynotobugs.confidence.mockito4;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class MatchesTest
{

    @Test
    void test()
    {
        assertThat(new Matches<>("123"),
            new AllOf<>(
                new Passes<>("123"::equals),
                new Fails<>(arg -> false),
                new Expects("matches \"123\"")
            ));
    }

}