package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class IsTest
{
    @Test
    void testValue()
    {
        assertThat(new Is<>(3),
            new AllOf<>(
                new Matches<>(3),
                new Mismatches<>(4, "<4>"),
                new Expects("<3>")));
    }


    @Test
    void testMatch()
    {
        assertThat(new Is<>(new Anything()),
            new AllOf<>(
                new Matches<>("12", 1, new Object()),
                new Expects("<anything>")));
    }


    @Test
    void testMismatch()
    {
        assertThat(new Is<>(new Nothing()),
            new AllOf<>(
                new Mismatches<>(1, "was something"),
                new Expects("<nothing>")));
    }
}