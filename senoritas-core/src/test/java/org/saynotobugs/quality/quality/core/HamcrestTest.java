package org.saynotobugs.quality.quality.core;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


class HamcrestTest
{
    @Test
    void test()
    {
        assertThat(new Hamcrest<>(Matchers.equalTo(123)),
            new AllOf<>(
                new Passes<>(123),
                new Fails<>(12, "was <12>"),
                new Expects("<123>")
            ));
    }
}