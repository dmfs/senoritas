package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class InstanceOfTest
{
    @Test
    void test()
    {
        assertThat(new InstanceOf<>(Number.class),
            new AllOf<>(
                new Passes<Object>(1, 1L, 1f),
                new Fails<>("string", "instance of <class java.lang.String>"),
                new Fails<>(new Object(), "instance of <class java.lang.Object>"),
                new Expects("instance of <class java.lang.Number>")
            ));
    }
}