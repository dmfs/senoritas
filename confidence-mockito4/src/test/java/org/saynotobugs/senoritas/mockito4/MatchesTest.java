package org.saynotobugs.senoritas.mockito4;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


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