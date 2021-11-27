package org.saynotobugs.senoritas.matcher.core;

import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.matcher.Expects;
import org.saynotobugs.senoritas.matcher.matcher.Matches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class AnythingTest
{

    @Test
    void test()
    {
        assertThat(
            new Anything(),
            new AllOf<>(
                new Matches<>(123),
                new Matches<Object>("abc"),
                new Matches<Object>(new String[] { "a", "b", "c" }, new int[] { 1, 2, 3 }),
                new Matches<>(new Object[] { null }),
                new Matches<Object>(new Seq<>(1, 2, 3)),
                new Expects("<anything>")));
    }

}