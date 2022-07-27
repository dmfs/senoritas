package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Passes;
import org.saynotobugs.quality.quality.test.Fails;

import static org.saynotobugs.quality.Assertion.assertThat;


class ArrayThatTest
{

    @Test
    void test()
    {
        assertThat(new int[] { 1, 2, 3 }, new ArrayThat(new Contains<>(3)));

        assertThat(new ArrayThat(new Contains<>(3)),
            new AllOf<>(
                new Passes<Object>(new int[] { 1, 2, 3 }, new int[] { 3 }, new int[] { 3, 3, 3, 3, 3 }),
                new Fails<Object>(new int[] {}, "(1) array that did not contain <3>"),
                new Fails<Object>(new int[] { 1, 2, 4 }, "(1) array that did not contain <3>"),
                new Fails<>("abc", "(0) not an array"),
                new Expects("(0) an array\n  and\n  (1) array that contains <3>")
            ));
    }

}