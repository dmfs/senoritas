package org.saynotobugs.confidence.quality.comparable;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.grammar.Has;
import org.saynotobugs.confidence.test.quality.Description;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class LessThanOrEqualToTest
{
    @Test
    void test()
    {
        assertThat(new LessThanOrEqualTo<>(10),
            new AllOf<>(
                new Passes<>(10, 9, 8, 7, 9, -1),
                new Fails<>(11, "<11>"),
                new Has<>(new Description("less than or equal to <10>"))
            ));
    }
}