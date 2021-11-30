package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.matcher.DescribesAs;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.saynotobugs.senoritas.Assertion.assertThat;


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
        // assertThat(new ValueDescription(Map.of("abc", 123)), describesAs("{\n  \"abc\" :  <123>\n}"));
        assertThat(new ValueDescription(empty()), new DescribesAs("<empty>"));
        assertThat(new ValueDescription(of(123)), new DescribesAs("<present <123>>"));
        assertThat(new ValueDescription(new String[] { "a", "b", "c" }), new DescribesAs("[ \"a\",\n  \"b\",\n  \"c\" ]"));
    }
}