package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.Nothing;
import org.saynotobugs.quality.test.quality.DescribesAs;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;

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