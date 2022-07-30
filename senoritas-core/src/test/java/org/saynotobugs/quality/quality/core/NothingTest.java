package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.DescribesAs;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;

import static org.saynotobugs.quality.Assertion.assertThat;


class NothingTest
{

    @Test
    void test()
    {
        assertThat(new Nothing(),
            new AllOf<>(
                new Fails<Object>("abc", new DescribesAs("was something")),
                new Fails<Object>(123, new DescribesAs("was something")),
                new Fails<Object>(null, new DescribesAs("was something")),
                new Expects("<nothing>")));
    }

}