package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class AllOfTest
{
    @Test
    void testMatch()
    {
        assertThat(new AllOf<>(), new Matches<>(123, "abc"));
        assertThat(new AllOf<>(new Anything()), new Matches<>(123, "abc"));
        assertThat(new AllOf<>(new Anything(), new Anything()), new Matches<>(123, "abc"));
    }


    @Test
    void testMismatch()
    {
        assertThat(new AllOf<>(new Nothing()), new Mismatches<>(123, "{ was something }"));
        assertThat(new AllOf<>(new Anything(), new Nothing()), new Mismatches<>(123, "{ ...\n  was something }"));
        assertThat(new AllOf<>(new Nothing(), new Anything(), new Anything()), new Mismatches<>(123, "{ was something\n  ... }"));
    }
}