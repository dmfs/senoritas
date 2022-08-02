package org.saynotobugs.quality.test.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;

import java.util.regex.Pattern;

import static org.saynotobugs.quality.Assertion.assertThat;


class DescribesAsTest
{

    @Test
    void test()
    {
        assertThat(new DescribesAs("123"),
            new AllOf<>(
                new Passes<>(scribe -> scribe.append("123")),
                new Fails<>(scribe -> scribe.append("abc"), "described as\n  ----\n  \"abc\"\n  ----"),
                new Expects("describes as\n  ----\n  \"123\"\n  ----")
            ));
    }


    @Test
    void testPattern()
    {
        assertThat(new DescribesAs(Pattern.compile("\\w123\\w")),
            new AllOf<>(
                new Passes<>(scribe -> scribe.append("a123b")),
                new Fails<>(scribe -> scribe.append("ab123"), "described as\n  ----\n  \"ab123\" mismatched pattern <\\\\w123\\\\w>\n  ----"),
                new Expects("describes as\n  ----\n  matches pattern <\\\\w123\\\\w>\n  ----")
            ));
    }

}