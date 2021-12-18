package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class InstanceOfTest
{
    @Test
    void test()
    {
        assertThat(new InstanceOf<>(Number.class),
            new AllOf<>(
                new Matches<Object>(1, 1L, 1f),
                new Mismatches<>("string", "instance of <class java.lang.String>"),
                new Mismatches<>(new Object(), "instance of <class java.lang.Object>"),
                new Expects("instance of <class java.lang.Number>")
            ));
    }
}