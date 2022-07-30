package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


class PassesPatternTest
{
    @Test
    void test()
    {
        assertThat(new MatchesPattern("^.*123.*$"),
            new AllOf<>(
                new Passes<>("123", "abc123xyz"),
                new Fails<>("12", "\"12\" mismatched pattern <^.*123.*$>"),
                new Fails<>("abc", "\"abc\" mismatched pattern <^.*123.*$>"),
                new Expects("matches pattern <^.*123.*$>")
            ));
    }
}