package org.saynotobugs.quality.description;

import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.test.quality.DescribesAs;

import static org.dmfs.jems2.iterable.EmptyIterable.emptyIterable;
import static org.saynotobugs.quality.Assertion.assertThat;


class IterableDescriptionTest
{
    @Test
    void testEmpty()
    {
        assertThat(
            new IterableDescription(emptyIterable()),
            new DescribesAs("[  ]"));
    }


    @Test
    void testOne()
    {
        assertThat(
            new IterableDescription(new Seq<>("abc")),
            new DescribesAs("[ \"abc\" ]"));
    }


    @Test
    void testTwo()
    {
        assertThat(
            new IterableDescription(new Seq<>("abc", "xyz")),
            new DescribesAs("[ \"abc\",\n  \"xyz\" ]"));
    }


    @Test
    void testNested()
    {
        assertThat(
            new IterableDescription(new Seq<>(new Seq<>(1, 2, 3), new Seq<>("a", "b", "c"))),
            new DescribesAs("[ " +
                "[" +
                " <1>,\n" +
                "    <2>,\n" +
                "    <3> " +
                "],\n" +
                "  [ \"a\"" +
                ",\n" +
                "    \"" +
                "b\",\n" +
                "    \"c\" ] ]"));
    }
}