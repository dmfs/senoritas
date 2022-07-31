package org.saynotobugs.quality.quality;

import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.Anything;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


class AnythingTest
{

    @Test
    void test()
    {
        assertThat(
            new Anything(),
            new AllOf<>(
                new Passes<>(123),
                new Passes<Object>("abc"),
                new Passes<Object>(new String[] { "a", "b", "c" }, new int[] { 1, 2, 3 }),
                new Passes<>(new Object[] { null }),
                new Passes<Object>(new Seq<>(1, 2, 3)),
                new Expects("<anything>")));
    }

}