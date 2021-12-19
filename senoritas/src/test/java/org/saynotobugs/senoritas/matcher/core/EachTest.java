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
    void testDescribedCtor()
    {
        assertThat(new Each<>("all elements", new LessThan<>(3)),
            new AllOf<>(
                new Matches<>(asList(0, 1, 2), asList(0, 0, 0), emptyList()),
                new Mismatches<>(asList(1, 4, 2), "elements [...\n  1:  <4>\n  ...]"),
                new Expects("all elements less than <3>")
            ));
    }
}