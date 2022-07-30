package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.DescribesAs;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


class SameAsTest
{

    @Test
    void test()
    {
        Integer i = new Integer(123);
        assertThat(new SameAs<>(i),
            new AllOf<>(
                new Passes<>(i),
                new Fails<>(123, new DescribesAs("<123>")),
                new Expects("<123>")));
    }

}