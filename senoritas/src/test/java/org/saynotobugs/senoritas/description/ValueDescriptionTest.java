package org.saynotobugs.senoritas.description;

import org.dmfs.jems2.iterable.Seq;
import org.junit.jupiter.api.Test;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.saynotobugs.senoritas.Hamcrest.describesAs;


class ValueDescriptionTest
{
    @Test
    void test()
    {
        assertThat(new ValueDescription<>(null), describesAs("<null>"));
        assertThat(new ValueDescription<>(123), describesAs("<123>"));
        assertThat(new ValueDescription<>("abc"), describesAs("\"abc\""));
        assertThat(new ValueDescription<>(true), describesAs("<true>"));
        assertThat(new ValueDescription<>(new Seq<>(5, "abc", 7)), describesAs("[\n  <5>,\n  \"abc\",\n  <7>\n]"));
        // assertThat(new ValueDescription<>(Map.of("abc", 123)), describesAs("{\n  \"abc\" :  <123>\n}"));
        assertThat(new ValueDescription<>(empty()), describesAs("<empty>"));
        assertThat(new ValueDescription<>(of(123)), describesAs("<present <123> >"));
        assertThat(new ValueDescription<>(new String[] { "a", "b", "c" }), describesAs("[\n  \"a\",\n  \"b\",\n  \"c\"\n]"));
    }
}