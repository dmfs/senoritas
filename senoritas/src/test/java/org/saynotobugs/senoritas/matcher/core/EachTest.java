package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.saynotobugs.senoritas.Assertion.assertThat;


class EachTest
{
    @Test
    void testSimpleCtor()
    {
        assertThat(new Each<>(new LessThan<>(3)),
            new AllOf<>(
                new Matches<>(asList(0, 1, 2), asList(0, 0, 0), emptyList()),
                new Mismatches<>(asList(1, 4, 2), "elements [...\n  1:  <4>\n  ...]"),
                new Expects("each element less than <3>")
            ));
    }


    @Test
    void testMultipleCtor()
    {
        assertThat(new Each<>(new LessThan<>(3), new GreaterThan<>(0)),
            new AllOf<>(
                new Matches<Iterable<Integer>>(asList(1, 1, 2), asList(2, 2, 2), emptyList()),
                new Mismatches<Iterable<Integer>>(asList(0, 4, 2), "elements [0:  { ...\n    <0> },\n  1:  { <4>\n    ... }\n  ...]"),
                new Mismatches<Iterable<Integer>>(asList(1, 4, 2), "elements [...\n  1:  { <4>\n    ... }\n  ...]"),
                new Expects("each element less than <3>\n  and\n  greater than <0>")
            ));
    }
}