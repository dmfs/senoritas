package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.HasSize;
import org.saynotobugs.quality.quality.LessThan;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

import java.util.Collection;
import java.util.HashSet;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.saynotobugs.quality.Assertion.assertThat;


class HasSizeTest
{

    @Test
    void testIntCtor()
    {
        assertThat(new HasSize(3),
            new AllOf<>(
                new Passes<>(asList(1, 2, 3), new HashSet<>(asList("a", "b", "c"))),
                new Fails<>(emptyList(), "had size <0>"),
                new Fails<Collection<?>>(asList(1, 2), "had size <2>"),
                new Fails<Collection<?>>(asList(1, 2, 3, 4), "had size <4>"),
                new Expects("has size <3>")));
    }


    @Test
    void testMatcherCtor()
    {
        assertThat(new HasSize(new LessThan<>(4)),
            new AllOf<>(
                new Passes<>(asList(1, 2, 3), new HashSet<>(asList("a", "b")), emptyList()),
                new Fails<>(asList(1, 2, 3, 4), "had size <4>"),
                new Fails<Collection<?>>(asList(1, 2, 3, 4, 5), "had size <5>"),
                new Expects("has size less than <4>")));
    }

}