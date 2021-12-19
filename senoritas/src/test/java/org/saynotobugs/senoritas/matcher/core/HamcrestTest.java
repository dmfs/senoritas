package org.saynotobugs.senoritas.matcher.core;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.saynotobugs.senoritas.matcher.test.Expects;
import org.saynotobugs.senoritas.matcher.test.Matches;
import org.saynotobugs.senoritas.matcher.test.Mismatches;

import static org.saynotobugs.senoritas.Assertion.assertThat;


class HamcrestTest
{
    @Test
    void test()
    {
        assertThat(new Hamcrest<>(Matchers.equalTo(123)),
            new AllOf<>(
                new Matches<>(123),
                new Mismatches<>(12, "was <12>"),
                new Expects("<123>")
            ));
    }
}