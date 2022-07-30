package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


class HasLengthTest
{
    @Test
    void testIntCtor()
    {
        assertThat(new HasLength(3),
            new AllOf<>(
                new Passes<>("123", "abc"),
                new Fails<>("", "had length <0>"),
                new Fails<>("12", "had length <2>"),
                new Fails<>("1234", "had length <4>"),
                new Expects("has length <3>")));
    }


    @Test
    void testMatcherCtor()
    {
        assertThat(new HasLength(new LessThan<>(4)),
            new AllOf<>(
                new Passes<>("", "12", "abc"),
                new Fails<>("abcd", "had length <4>"),
                new Fails<>("abcde", "had length <5>"),
                new Expects("has length less than <4>")));
    }
}