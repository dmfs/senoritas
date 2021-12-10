package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class NotTest
{

    @Test
    void test()
    {
        assertThat(new Not<>(new Anything()),
            new AllOf<>(
                new Mismatches<>("123", new DescribesAs("\"123\" <anything>")),
                new Expects("not <anything>")));

        assertThat(new Not<>(new Nothing()),
            new AllOf<>(
                new Matches<>("123"),
                new Expects("not <nothing>")));
    }

}