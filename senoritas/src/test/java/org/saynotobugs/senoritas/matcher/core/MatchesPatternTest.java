package org.saynotobugs.senoritas.matcher.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class MatchesPatternTest
{
    @Test
    void test()
    {
        assertThat(new MatchesPattern("^.*123.*$"),
            new AllOf<>(
                new Matches<>("123", "abc123xyz"),
                new Mismatches<>("12", "\"12\" mismatched pattern <^.*123.*$>"),
                new Mismatches<>("abc", "\"abc\" mismatched pattern <^.*123.*$>"),
                new Expects("matches pattern <^.*123.*$>")
            ));
    }
}