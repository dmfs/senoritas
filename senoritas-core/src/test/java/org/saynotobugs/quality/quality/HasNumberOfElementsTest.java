package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.HasNumberOfElements;
import org.saynotobugs.quality.quality.LessThan;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.saynotobugs.quality.Assertion.assertThat;


class HasNumberOfElementsTest
{

    @Test
    void testIntCtor()
    {
        assertThat(new HasNumberOfElements(3),
            new AllOf<>(
                new Passes<>(asList(1, 2, 3), new HashSet<>(asList("a", "b", "c"))),
                new Fails<Iterable<?>>(emptyList(), "had <0> elements"),
                new Fails<Iterable<?>>(asList(1, 2), "had <2> elements"),
                new Fails<Iterable<?>>(asList(1, 2, 3, 4), "had <4> elements"),
                new Expects("has <3> elements")));
    }


    @Test
    void testMatcherCtor()
    {
        assertThat(new HasNumberOfElements(new LessThan<>(4)),
            new AllOf<>(
                new Passes<>(asList(1, 2, 3), new HashSet<>(asList("a", "b")), emptyList()),
                new Fails<Iterable<?>>(asList(1, 2, 3, 4), "had <4> elements"),
                new Fails<Iterable<?>>(asList(1, 2, 3, 4, 5), "had <5> elements"),
                new Expects("has less than <4> elements")));
    }

}