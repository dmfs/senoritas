package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.Each;
import org.saynotobugs.quality.quality.GreaterThan;
import org.saynotobugs.quality.quality.LessThan;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.saynotobugs.quality.Assertion.assertThat;


class EachTest
{
    @Test
    void testSimpleCtor()
    {
        assertThat(new Each<>(new LessThan<>(3)),
            new AllOf<>(
                new Passes<>(asList(0, 1, 2), asList(0, 0, 0), emptyList()),
                new Fails<>(asList(1, 4, 2), "elements [...\n  1:  <4>\n  ...]"),
                new Expects("each element less than <3>")
            ));
    }


    @Test
    void testMultipleCtor()
    {
        assertThat(new Each<>(new LessThan<>(3), new GreaterThan<>(0)),
            new AllOf<>(
                new Passes<Iterable<Integer>>(asList(1, 1, 2), asList(2, 2, 2), emptyList()),
                new Fails<Iterable<Integer>>(asList(0, 4, 2), "elements [0:  { ...\n    <0> },\n  1:  { <4>\n    ... }\n  ...]"),
                new Fails<Iterable<Integer>>(asList(1, 4, 2), "elements [...\n  1:  { <4>\n    ... }\n  ...]"),
                new Expects("each element less than <3>\n  and\n  greater than <0>")
            ));
    }
}