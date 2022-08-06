package org.saynotobugs.confidence.quality.object;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.grammar.Has;
import org.saynotobugs.confidence.test.quality.DescribesAs;
import org.saynotobugs.confidence.test.quality.Description;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class ToStringTest
{

    @Test
    void test()
    {
        assertThat(new ToString("123"),
            new AllOf<>(
                new Passes<>("123", 123),
                new Fails<>("1234", new DescribesAs("toString() \"1234\"")),
                new Has<>(new Description("toString() \"123\""))));
    }

}