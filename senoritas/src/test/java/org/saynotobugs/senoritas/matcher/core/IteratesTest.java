package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.EmptyIterable;
import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class IteratesTest
{

    @Test
    void testEmpty()
    {
        assertThat(new Iterates<>(/* nothing */),
            new AllOf<>(
                new Matches<>(new EmptyIterable<>()),
                new Mismatches<>(new Seq<>(1), "iterated [ 0: unexpected <1> ]"),
                new Mismatches<>(new Seq<>(1, 2, 3), "iterated [ 0: unexpected <1>,\n  1: unexpected <2>,\n  2: unexpected <3> ]"),
                new Expects("iterates [  ]")
            ));
    }


    @Test
    void testOneValue()
    {
        assertThat(new Iterates<>(1),
            new AllOf<>(
                new Matches<>(new Seq<>(1), new Seq<>(1)),
                new Mismatches<Iterable<Integer>>(new EmptyIterable<>(), "iterated [ 0: missing <1> ]"),
                new Mismatches<Iterable<Integer>>(new Seq<>(1, 2, 3), "iterated [ ...\n  1: unexpected <2>,\n  2: unexpected <3> ]"),
                new Expects("iterates [ 0: <1> ]")
            ));
    }


    @Test
    void testMultipleValues()
    {
        assertThat(new Iterates<>(1, 2, 3),
            new AllOf<>(
                new Matches<>(new Seq<>(1, 2, 3), new Seq<>(1, 2, 3)),
                new Mismatches<Iterable<Integer>>(new EmptyIterable<>(), "iterated [ 0: missing <1>,\n  1: missing <2>,\n  2: missing <3> ]"),
                new Mismatches<Iterable<Integer>>(new Seq<>(0, 2, 3, 4), "iterated [ 0: <0>\n  ...\n  3: unexpected <4> ]"),
                new Expects("iterates [ 0: <1>,\n  1: <2>,\n  2: <3> ]")
            ));
    }


    @Test
    void testOneMatcher()
    {
        assertThat(new Iterates<>(new LessThan<>(1)),
            new AllOf<>(
                new Matches<>(new Seq<>(-1), new Seq<>(0)),
                new Mismatches<Iterable<Integer>>(new EmptyIterable<>(), "iterated [ 0: missing less than <1> ]"),
                new Mismatches<Iterable<Integer>>(new Seq<>(1, 0, 3), "iterated [ 0: <1>,\n  1: unexpected <0>,\n  2: unexpected <3> ]"),
                new Expects("iterates [ 0: less than <1> ]")
            ));
    }


    @Test
    void testMultipleMatchers()
    {
        assertThat(new Iterates<>(new LessThan<>(1), new GreaterThan<>(2), new LessThan<>(3)),
            new AllOf<>(
                new Matches<>(new Seq<>(0, 3, 2), new Seq<>(-1, 100, 0)),
                new Mismatches<Iterable<Integer>>(new EmptyIterable<>(),
                    "iterated [ 0: missing less than <1>,\n  1: missing greater than <2>,\n  2: missing less than <3> ]"),
                new Mismatches<Iterable<Integer>>(new Seq<>(0, 2, 3, 4), "iterated [ ...\n  1: <2>,\n  2: <3>,\n  3: unexpected <4> ]"),
                new Expects("iterates [ 0: less than <1>,\n  1: greater than <2>,\n  2: less than <3> ]")
            ));
    }

}