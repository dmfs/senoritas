package org.saynotobugs.confidence.quality.charsequence;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.test.quality.DescribesAs;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class EmptyCharSequenceTest
{

    @Test
    void test()
    {
        assertThat(new EmptyCharSequence(),
            new AllOf<>(
                new Passes<>(""),
                new Fails<>(" ", new DescribesAs("\" \"")),
                new Fails<>("123", new DescribesAs("\"123\"")),
                new Expects("<empty>")
            ));
    }

}