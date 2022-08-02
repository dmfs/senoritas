package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.Null;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Passes;
import org.saynotobugs.quality.test.quality.Fails;

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