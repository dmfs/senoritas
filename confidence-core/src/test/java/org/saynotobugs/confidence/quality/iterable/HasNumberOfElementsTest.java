package org.saynotobugs.confidence.quality.iterable;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.comparable.LessThan;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.grammar.Has;
import org.saynotobugs.confidence.test.quality.Description;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.saynotobugs.confidence.Assertion.assertThat;


class HasNumberOfElementsTest
{

    @Test
    void testIntCtor()
    {
        assertThat(new HasNumberOfElements(3),
            new AllOf<>(
                new Passes<>(asList(1, 2, 3), new HashSet<>(asList("a", "b", "c"))),
                new Fails<Iterable<?>>(emptyList(), "<0> elements"),
                new Fails<Iterable<?>>(asList(1, 2), "<2> elements"),
                new Fails<Iterable<?>>(asList(1, 2, 3, 4), "<4> elements"),
                new Has<>(new Description("<3> elements"))));
    }


    @Test
    void testMatcherCtor()
    {
        assertThat(new HasNumberOfElements(new LessThan<>(4)),
            new AllOf<>(
                new Passes<>(asList(1, 2, 3), new HashSet<>(asList("a", "b")), emptyList()),
                new Fails<Iterable<?>>(asList(1, 2, 3, 4), "<4> elements"),
                new Fails<Iterable<?>>(asList(1, 2, 3, 4, 5), "<5> elements"),
                new Has<>(new Description("less than <4> elements"))));
    }

}