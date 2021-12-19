package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.saynotobugs.senoritas.Assertion.assertThat;


class HasNumberOfElementsTest
{

    @Test
    void testIntCtor()
    {
        assertThat(new HasNumberOfElements(3),
            new AllOf<>(
                new Matches<>(asList(1, 2, 3), new HashSet<>(asList("a", "b", "c"))),
                new Mismatches<Iterable<?>>(emptyList(), "had <0> elements"),
                new Mismatches<Iterable<?>>(asList(1, 2), "had <2> elements"),
                new Mismatches<Iterable<?>>(asList(1, 2, 3, 4), "had <4> elements"),
                new Expects("has <3> elements")));
    }


    @Test
    void testMatcherCtor()
    {
        assertThat(new HasNumberOfElements(new LessThan<>(4)),
            new AllOf<>(
                new Matches<>(asList(1, 2, 3), new HashSet<>(asList("a", "b")), emptyList()),
                new Mismatches<Iterable<?>>(asList(1, 2, 3, 4), "had <4> elements"),
                new Mismatches<Iterable<?>>(asList(1, 2, 3, 4, 5), "had <5> elements"),
                new Expects("has less than <4> elements")));
    }

}