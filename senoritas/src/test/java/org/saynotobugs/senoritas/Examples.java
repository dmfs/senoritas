package org.saynotobugs.senoritas;

import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.description.Composite;
import org.saynotobugs.senoritas.description.NumberDescription;
import org.saynotobugs.senoritas.description.TextDescription;
import org.saynotobugs.senoritas.matcher.core.*;
import org.saynotobugs.senoritas.matcher.matcher.Matches;
import org.saynotobugs.senoritas.matcher.matcher.Mismatches;
import org.saynotobugs.senoritas.verdict.PassIf;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.Matchers.contains;
import static org.saynotobugs.senoritas.Assertion.assertThat;


@Disabled
public final class Examples
{
    @Test
    void testEqualTo()
    {
        assertThat(1, new EqualTo<>(2));
    }


    @Test
    void testEqualToArray()
    {
        assertThat(new int[][] { new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 }, new int[] { 6, 7, 8, 9 } },
            new EqualTo<>(new int[][] { new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 }, new int[] { 6, 7, 8 } }));
        assertThat(new Integer[][] { new Integer[] { 1, 2, 3 }, new Integer[] { 4, 5, 6 }, new Integer[] { 6, 7, 8 } },
            new EqualTo<>(new Integer[][] { new Integer[] { 1, 2, 3 }, new Integer[] { 4, 5, 6 }, new Integer[] { 6, 7, 8 } }));
        assertThat(new Integer[][] { new Integer[] { 1, 2, 3 }, new Integer[] { 4, 55, 6 }, new Integer[] { 6, 7, 88 } },
            new EqualTo<>(new Integer[][] { new Integer[] { 1, 2, 3 }, new Integer[] { 4, 5, 6 }, new Integer[] { 6, 7, 8 } }));
    }


    @Test
    void testLessThan()
    {
        assertThat(10, new LessThan<>(2));
    }


    @Test
    void testIterableLessThan()
    {
        assertThat(new Seq<>(1, 2, 10, 3, 4), new Iterates<>(new LessThan<>(5), new LessThan<>(5), new LessThan<>(5), new LessThan<>(5), new LessThan<>(5)));
    }


    @Test
    void testEachLessThan()
    {
        assertThat(new Seq<>(1, 2, 10, 3, 4), new Each<>(new LessThan<>(5)));
    }


    @Test
    void testIterable()
    {
        assertThat(new Seq<>(1, 2, 10, 3, 4, 6, 7), new Iterates<>(1, 2, 11, 5, 4, 6, 7, 23, 323));
    }


    @Test
    void testContainsOne()
    {
        assertThat(new Seq<>(1, 2, 10, 3, 4), new Contains<>(15));
    }


    @Test
    void testContainsMany()
    {
        assertThat(new Seq<>(1, 2, 10, 3, 4), new Contains<>(1, 32, 11, 4));
    }


    @Test
    void testIterableOfOptionals()
    {
        assertThat(new Seq<>(of(1), of(2), of(3), empty()), new Iterates<>(new Present<>(1), new Present<>(2), new Present<>(4)));
    }


    @Test
    void testIn()
    {
        assertThat(3, new Is<>(new In<>(4, 5, 6)));
    }


    @Test
    void testNotIn()
    {
        assertThat(3, new Is<>(new Not<>(new In<>(4, 3, 6))));
    }


    @Test
    void containsInAnyOrder()
    {
        assertThat(new Seq<>(3, 5, 6, 3), new IteratesInAnyOrder<>(6, 3, 5, 11));
    }


    @Test
    void testNestedIterable()
    {
        assertThat(new Seq<>(new Seq<>(1, 2, 3), new Seq<>(4, 4, 5), new Seq<>(7, 8, 9)),
            new Iterates<>(new Iterates<>(1, 2, 3), new Iterates<>(4, 5, 6), new Iterates<>(7, 8, 9)));
    }


    @Test
    void testHaving()
    {
        assertThat(123, new Having<>("hashcode", Objects::hashCode, new EqualTo<>(125)));
    }


    @Test
    void testHaving2()
    {
        assertThat(123, new Having<>(new TextDescription("has hash"), new TextDescription("had hash"), Objects::hashCode, new EqualTo<>(125)));
    }


    @Test
    void testHamcrestCompat()
    {
        assertThat(new Seq<>(1, 2, 5, 10, 11), new Hamcrest<>(contains(1, 2, 3, 10, 12)));
    }


    @Test
    void testSupplies()
    {
        assertThat(() -> new Seq<>(1, 2, 5), new Supplies<>(new Iterates<>(1, 2, 3)));
    }


    @Test
    void testMatches()
    {
        assertThat(new Matcher<String>()
        {
            @Override
            public Verdict match(String actual)
            {
                return new PassIf(actual.length() == 3, new Composite(
                    new TextDescription("String length was"),
                    new NumberDescription(actual.length())));
            }


            @Override
            public Description expectation()
            {
                return new TextDescription("String with length 3");
            }
        }, new Matches<>(
            "123",
            "456",
            "7654",
            "12"
        ));
    }


    @Test
    void testMismatches()
    {
        assertThat(new Matcher<String>()
                   {
                       @Override
                       public Verdict match(String actual)
                       {
                           return new PassIf(actual.length() == 3, new Composite(
                               new TextDescription("String length was"),
                               new NumberDescription(actual.length())));
                       }


                       @Override
                       public Description expectation()
                       {
                           return new TextDescription("String with length 3");
                       }
                   },
            new AllOf<>(
                new Mismatches<>("1235"),
                new Mismatches<>("45634"),
                new Mismatches<>("765"),
                new Mismatches<>("1234", "String length was <3>"),
                new Mismatches<>("122"),
                new Mismatches<>("seeewe")
            ));
    }


    @Test
    void testHasToString()
    {
        assertThat("123", new HasToString(new EqualTo<>("123")));
        assertThat("124", new HasToString("123"));
    }


    @Test
    void parallel()
    {
        assertThat(new AtomicInteger(0), new Parallel<>(1000,
            new Having<>("modulo", i -> i.incrementAndGet() % 50, new LessThan<>(48))));
    }


    @Test
    void testNotAnyOf()
    {
        assertThat(new Seq<>(1, 2, 10, 11, 4), new Each<>(new NoneOf<>(new EqualTo<>(77), new EqualTo<>(10), new EqualTo<>(22), new GreaterThan<>(9))));
    }

}
