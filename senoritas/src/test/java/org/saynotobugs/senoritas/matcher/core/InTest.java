package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static java.util.Arrays.asList;
import static org.saynotobugs.senoritas.Assertion.assertThat;


class InTest
{

    @Test
    void testValues()
    {
        assertThat(new In<>(1, 2, 3),
            new AllOf<>(
                new Matches<>(1, 2, 3),
                new Mismatches<>(4, "<4> not in [ <1>,\n  <2>,\n  <3> ]"),
                new Mismatches<>(0, "<0> not in [ <1>,\n  <2>,\n  <3> ]"),
                new Expects("in [ <1>,\n  <2>,\n  <3> ]")));
    }


    @Test
    void testCollection()
    {
        assertThat(new In<>(asList(1, 2, 3)),
            new AllOf<>(
                new Matches<>(1, 2, 3),
                new Mismatches<>(4, "<4> not in [ <1>,\n  <2>,\n  <3> ]"),
                new Mismatches<>(0, "<0> not in [ <1>,\n  <2>,\n  <3> ]"),
                new Expects("in [ <1>,\n  <2>,\n  <3> ]")));
    }


    @Test
    void testMatchers()
    {
        assertThat(new In<>(new EqualTo<>(1), new EqualTo<>(2), new EqualTo<>(3)),
            new AllOf<>(
                new Matches<>(1, 2, 3),
                new Mismatches<>(4, "<4> not in { <1>,\n  <2>,\n  <3> }"),
                new Mismatches<>(0, "<0> not in { <1>,\n  <2>,\n  <3> }"),
                new Expects("in { <1>,\n  <2>,\n  <3> }")));
    }


    @Test
    void testMatcherIterable()
    {
        assertThat(new In<>(new Seq<>(new EqualTo<>(1), new EqualTo<>(2), new EqualTo<>(3))),
            new AllOf<>(
                new Matches<>(1, 2, 3),
                new Mismatches<>(4, "<4> not in { <1>,\n  <2>,\n  <3> }"),
                new Mismatches<>(0, "<0> not in { <1>,\n  <2>,\n  <3> }"),
                new Expects("in { <1>,\n  <2>,\n  <3> }")));
    }
}