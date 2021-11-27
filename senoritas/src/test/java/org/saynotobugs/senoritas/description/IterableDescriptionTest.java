package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.matcher.DescribesAs;

import static org.dmfs.jems2.iterable.EmptyIterable.emptyIterable;
import static org.saynotobugs.senoritas.Assertion.assertThat;


class IterableDescriptionTest
{
    @Test
    void testEmpty()
    {
        assertThat(
            new IterableDescription(emptyIterable()),
            new DescribesAs("[\n  \n]"));
    }


    @Test
    void testOne()
    {
        assertThat(
            new IterableDescription(new Seq<>("abc")),
            new DescribesAs("[\n  \"abc\"\n]"));
    }


    @Test
    void testTwo()
    {
        assertThat(
            new IterableDescription(new Seq<>("abc", "xyz")),
            new DescribesAs("[\n  \"abc\",\n  \"xyz\"\n]"));
    }


    @Test
    void testNested()
    {
        assertThat(
            new IterableDescription(new Seq<>(new Seq<>(1, 2, 3), new Seq<>("a", "b", "c"))),
            new DescribesAs("[\n  [\n    <1>,\n    <2>,\n    <3>\n  ],\n  [\n    \"a\",\n    \"b\",\n    \"c\"\n  ]\n]"));
    }
}