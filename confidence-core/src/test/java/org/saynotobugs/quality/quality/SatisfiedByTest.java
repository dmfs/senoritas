package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.SatisfiedBy;
import org.saynotobugs.quality.test.quality.DescribesAs;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


class SatisfiedByTest
{

    @Test
    void testMatch()
    {
        assertThat(new SatisfiedBy<>("12"),
            new AllOf<>(
                new Passes<>("12"::equals, s -> s.length() == 2),
                new Fails<>("123"::equals, new DescribesAs("not satisfied by \"12\"")),
                new Expects("satisfied by \"12\"")));
    }

}