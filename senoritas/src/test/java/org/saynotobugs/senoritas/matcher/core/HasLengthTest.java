package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class HasLengthTest
{
    @Test
    void testIntCtor()
    {
        assertThat(new HasLength(3),
            new AllOf<>(
                new Matches<>("123", "abc"),
                new Mismatches<>("", "had length <0>"),
                new Mismatches<>("12", "had length <2>"),
                new Mismatches<>("1234", "had length <4>"),
                new Expects("has length <3>")));
    }


    @Test
    void testMatcherCtor()
    {
        assertThat(new HasLength(new LessThan<>(4)),
            new AllOf<>(
                new Matches<>("", "12", "abc"),
                new Mismatches<>("abcd", "had length <4>"),
                new Mismatches<>("abcde", "had length <5>"),
                new Expects("has length less than <4>")));
    }
}