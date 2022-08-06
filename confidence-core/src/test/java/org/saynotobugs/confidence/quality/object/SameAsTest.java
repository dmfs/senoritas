package org.saynotobugs.confidence.quality.object;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.grammar.Has;
import org.saynotobugs.confidence.test.quality.DescribesAs;
import org.saynotobugs.confidence.test.quality.Description;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class SameAsTest
{

    @Test
    void test()
    {
        Integer i = new Integer(123);
        assertThat(new SameAs<>(i),
            new AllOf<>(
                new Passes<>(i),
                new Fails<>(123, new DescribesAs("<123>")),
                new Has<>(new Description("<123>"))));
    }

}