package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class SatisfiedByTest
{

    @Test
    void testMatch()
    {
        assertThat(new SatisfiedBy<>("12"),
            new AllOf<>(
                new Matches<>("12"::equals, s -> s.length() == 2),
                new Mismatches<>("123"::equals, new DescribesAs("not satisfied by \"12\"")),
                new Expects("satisfied by \"12\"")));
    }

}