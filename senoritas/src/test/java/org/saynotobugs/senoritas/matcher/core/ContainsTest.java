package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

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
                new Mismatches<Iterable<? extends Integer>>(emptyIterable(), new DescribesAs("did not contain <123>")),
                new Mismatches<Iterable<? extends Integer>>(new Seq<>(1, 2, 3), new DescribesAs("did not contain <123>")),
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
                new Mismatches<Iterable<? extends Integer>>(emptyIterable(), new DescribesAs("{ did not contain <1>\n  and\n  did not contain <2>\n  and\n  did not contain <3> }")),
                new Mismatches<Iterable<? extends Integer>>(new Seq<>(1, 2), new DescribesAs("{ ...\n  did not contain <3> }")),
                new Mismatches<Iterable<? extends Integer>>(new Seq<>(1, 2, 2, 2), new DescribesAs("{ ...\n  did not contain <3> }")),
                new Expects("contains <1>\n  and\n  contains <2>\n  and\n  contains <3>")
            ));
    }
}