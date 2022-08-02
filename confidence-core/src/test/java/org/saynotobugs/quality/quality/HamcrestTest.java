package org.saynotobugs.quality.quality;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.Hamcrest;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

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