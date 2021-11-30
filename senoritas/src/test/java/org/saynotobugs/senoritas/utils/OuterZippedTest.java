package org.saynotobugs.senoritas.utils;

import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.core.Iterates;

import static org.dmfs.jems2.iterable.EmptyIterable.emptyIterable;
import static org.saynotobugs.senoritas.Assertion.assertThat;


class OuterZippedTest
{

    @Test
    void testEmpty()
    {
        assertThat(new OuterZipped<String, String, Boolean>(emptyIterable(), emptyIterable(), (l, r) -> l.isPresent() && r.isPresent()),
            new Iterates<>(/* nothing */));
    }


    @Test
    void testLeftSided()
    {
        assertThat(new OuterZipped<String, String, Boolean>(new Seq<>("a", "b"), emptyIterable(), (l, r) -> l.isPresent() && !r.isPresent()),
            new Iterates<>(true, true));
    }


    @Test
    void testRightSided()
    {
        assertThat(new OuterZipped<String, String, Boolean>(emptyIterable(), new Seq<>("a", "b"), (l, r) -> !l.isPresent() && r.isPresent()),
            new Iterates<>(true, true));
    }


    @Test
    void testBothSided()
    {
        assertThat(new OuterZipped<String, String, Boolean>(new Seq<>("1", "2"), new Seq<>("a", "b"), (l, r) -> l.isPresent() && r.isPresent()),
            new Iterates<>(true, true));
    }
}