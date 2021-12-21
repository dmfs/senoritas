package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.DescribesAs;

import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.saynotobugs.senoritas.Assertion.assertThat;
import static org.saynotobugs.senoritas.matcher.test.Test.describesAs;


class ValueDescriptionTest
{
    @Test
    void test()
    {
        assertThat(new ValueDescription(null), new DescribesAs("<null>"));
        assertThat(new ValueDescription(123), new DescribesAs("<123>"));
        assertThat(new ValueDescription("abc"), new DescribesAs("\"abc\""));
        assertThat(new ValueDescription(true), new DescribesAs("<true>"));
        assertThat(new ValueDescription(new Seq<>(5, "abc", 7)), new DescribesAs("[ <5>,\n  \"abc\",\n  <7> ]"));
        assertThat(new ValueDescription(new Map.Entry<String, Integer>()
        {
            @Override
            public String getKey()
            {
                return "abc";
            }


            @Override
            public Integer getValue()
            {
                return 123;
            }


            @Override
            public Integer setValue(Integer value)
            {
                throw new AssertionError("unexpected call to setValue");
            }

        }), describesAs("\"abc\": <123>"));
        assertThat(new ValueDescription(empty()), new DescribesAs("<empty>"));
        assertThat(new ValueDescription(of(123)), new DescribesAs("<present <123>>"));
        assertThat(new ValueDescription(new String[] { "a", "b", "c" }), new DescribesAs("[ \"a\",\n  \"b\",\n  \"c\" ]"));
        assertThat(new ValueDescription(new TextDescription("123")), new DescribesAs("\n  ----\n  123\n  ----"));
    }


    @Test
    void testMap()
    {
        Map<String, Integer> map = new HashMap<>();
        map.put("abc", 123);
        assertThat(new ValueDescription(map), new DescribesAs("{ \"abc\": <123> }"));
    }
}