package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.InstanceOf;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


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