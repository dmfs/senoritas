package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Passes;
import org.saynotobugs.quality.quality.test.Fails;

import static org.saynotobugs.quality.Assertion.assertThat;


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