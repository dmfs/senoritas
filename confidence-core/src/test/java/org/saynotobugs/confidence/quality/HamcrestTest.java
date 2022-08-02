package org.saynotobugs.confidence.quality;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


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