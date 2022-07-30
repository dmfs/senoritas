package org.saynotobugs.senoritas.mockito4;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.core.AllOf;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

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