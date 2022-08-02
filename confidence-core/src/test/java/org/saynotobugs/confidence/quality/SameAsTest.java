package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.DescribesAs;
import org.saynotobugs.confidence.test.quality.Expects;
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
                new Expects("<123>")));
    }

}