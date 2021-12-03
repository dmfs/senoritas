package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.matcher.DescribesAs;
import org.saynotobugs.senoritas.matcher.matcher.Expects;
import org.saynotobugs.senoritas.matcher.matcher.Matches;
import org.saynotobugs.senoritas.matcher.matcher.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class HasToStringTest
{

    @Test
    void test()
    {
        assertThat(new HasToString("123"),
            new AllOf<>(
                new Matches<>("123", 123),
                new Mismatches<>("1234", new DescribesAs("had toString() \"1234\"")),
                new Expects("has toString() \"123\"")));
    }

}