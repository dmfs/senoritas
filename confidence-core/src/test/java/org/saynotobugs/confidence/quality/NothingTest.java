package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.DescribesAs;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;

import static org.saynotobugs.confidence.Assertion.assertThat;


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