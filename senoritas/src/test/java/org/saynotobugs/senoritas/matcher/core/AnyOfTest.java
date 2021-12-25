package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class AnyOfTest
{
    @Test
    void test()
    {
        assertThat(new AnyOf<>(1, 2, 3),
            new AllOf<>(
                new Matches<>(1, 2, 3),
                new Mismatches<>(0, "<0> neither <1> nor \n  <2> nor \n  <3>"),
                new Mismatches<>(4, "<4> neither <1> nor \n  <2> nor \n  <3>"),
                new Expects("<1> or <2> or <3>")
            ));
    }


    @Test
    void testMatchers()
    {
        assertThat(new AnyOf<>(new EqualTo<>(1), new EqualTo<>(2), new EqualTo<>(3)),
            new AllOf<>(
                new Matches<>(1, 2, 3),
                new Mismatches<>(0, "<0> neither <1> nor \n  <2> nor \n  <3>"),
                new Mismatches<>(4, "<4> neither <1> nor \n  <2> nor \n  <3>"),
                new Expects("<1> or <2> or <3>")
            ));
    }
}