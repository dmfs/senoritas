package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.matcher.DescribesAs;
import org.saynotobugs.senoritas.matcher.matcher.Expects;
import org.saynotobugs.senoritas.matcher.matcher.Matches;
import org.saynotobugs.senoritas.matcher.matcher.Mismatches;

import static org.dmfs.jems2.iterable.EmptyIterable.emptyIterable;
import static org.saynotobugs.senoritas.Assertion.assertThat;


class ContainsTest
{

    @Test
    void testSingle()
    {
        assertThat(new Contains<>(123),
            new AllOf<>(
                new Matches<>(new Seq<>(123), new Seq<>(1, 2, 3, 123)),
                new Mismatches<Iterable<Integer>>(emptyIterable(), new DescribesAs("did not contain <123>")),
                new Mismatches<Iterable<Integer>>(new Seq<>(1, 2, 3), new DescribesAs("did not contain <123>")),
                new Expects("contains <123>")
            ));
    }


    @Test
    void testMultiple()
    {
        assertThat(new Contains<>(1, 2, 3),
            new AllOf<>(
                new Matches<>(
                    new Seq<>(1, 2, 3),
                    new Seq<>(3, 1, 2),
                    new Seq<>(3, 3, 3, 1, 1, 1, 2, 2, 2),
                    new Seq<>(0, 1, 2, 3, 123),
                    new Seq<>(3, 2, 3, 123, 1)),
                new Mismatches<Iterable<Integer>>(emptyIterable(), new DescribesAs("did not contain <1> and\ndid not contain <2> and\ndid not contain <3>")),
                new Mismatches<Iterable<Integer>>(new Seq<>(1, 2), new DescribesAs("...\ndid not contain <3>")),
                new Mismatches<Iterable<Integer>>(new Seq<>(1, 2, 2, 2), new DescribesAs("...\ndid not contain <3>")),
                new Expects("contains <1> and\ncontains <2> and\ncontains <3>")
            ));
    }
}