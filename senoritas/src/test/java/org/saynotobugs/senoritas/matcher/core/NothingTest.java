package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class NothingTest
{

    @Test
    void test()
    {
        assertThat(new Nothing(),
            new AllOf<>(
                new Mismatches<Object>("abc", new DescribesAs("was something")),
                new Mismatches<Object>(123, new DescribesAs("was something")),
                new Mismatches<Object>(null, new DescribesAs("was something")),
                new Expects("<nothing>")));
    }

}