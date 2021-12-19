package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import java.util.Collection;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.saynotobugs.senoritas.Assertion.assertThat;


class HasSizeTest
{

    @Test
    void testIntCtor()
    {
        assertThat(new HasSize(3),
            new AllOf<>(
                new Matches<>(asList(1, 2, 3), new HashSet<>(asList("a", "b", "c"))),
                new Mismatches<>(emptyList(), "had size <0>"),
                new Mismatches<Collection<?>>(asList(1, 2), "had size <2>"),
                new Mismatches<Collection<?>>(asList(1, 2, 3, 4), "had size <4>"),
                new Expects("has size <3>")));
    }


    @Test
    void testMatcherCtor()
    {
        assertThat(new HasSize(new LessThan<>(4)),
            new AllOf<>(
                new Matches<>(asList(1, 2, 3), new HashSet<>(asList("a", "b")), emptyList()),
                new Mismatches<>(asList(1, 2, 3, 4), "had size <4>"),
                new Mismatches<Collection<?>>(asList(1, 2, 3, 4, 5), "had size <5>"),
                new Expects("has size less than <4>")));
    }

}