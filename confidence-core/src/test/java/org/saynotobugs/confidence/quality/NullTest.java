package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Passes;
import org.saynotobugs.confidence.test.quality.Fails;

import static org.saynotobugs.confidence.Assertion.assertThat;


class NullTest
{
    @Test
    void test()
    {
        assertThat(new Null(),
            new AllOf<>(
                new Passes<>(new Object[] { null }),
                new Fails<>(123, "<123>"),
                new Expects("<null>")));
    }
}