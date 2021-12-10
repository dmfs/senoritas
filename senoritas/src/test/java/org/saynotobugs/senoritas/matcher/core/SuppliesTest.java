package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class SuppliesTest
{

    @Test
    void test()
    {
        assertThat(new Supplies<>(123), new AllOf<>(
            new Matches<>(() -> 123),
            new Mismatches<>(() -> null, new DescribesAs("supplied value <null>")),
            new Mismatches<>(() -> 1234, new DescribesAs("supplied value <1234>")),
            new Expects("supplies value <123>")
        ));
    }

}