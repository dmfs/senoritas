package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static java.util.Arrays.asList;
import static org.saynotobugs.senoritas.Assertion.assertThat;


class IteratesInAnyOrderTest
{
    @Test
    void test()
    {
        assertThat(new IteratesInAnyOrder<>(1, 2, 3),
            new AllOf<>(
                new Matches<>(asList(1, 2, 3), asList(1, 3, 2), asList(2, 1, 3), asList(2, 3, 1), asList(3, 1, 2), asList(3, 2, 1)),
                new Mismatches<>(asList(0, 1, 2, 3), "contained also [<0>],did not contain []"),
                new Mismatches<>(asList(1, 2), "contained also [],did not contain [<3>]"),
                new Mismatches<>(asList(-1, 0, 1, 2), "contained also [<-1>,\n    <0>],did not contain [<3>]"),
                new Expects("contains in any order [<1>,\n  <2>,\n  <3>]")
            ));
    }
}