package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class ArrayThatTest
{

    @Test
    void test()
    {
        assertThat(new int[] { 1, 2, 3 }, new ArrayThat(new Contains<>(3)));

        assertThat(new ArrayThat(new Contains<>(3)),
            new AllOf<>(
                new Matches<Object>(new int[] { 1, 2, 3 }, new int[] { 3 }, new int[] { 3, 3, 3, 3, 3 }),
                new Mismatches<Object>(new int[] {}, "array that did not contain <3>"),
                new Mismatches<Object>(new int[] { 1, 2, 4 }, "array that did not contain <3>"),
                new Mismatches<>("abc", "not an array"),
                new Expects("an array\n  and\n  array that contains <3>")
            ));
    }

}