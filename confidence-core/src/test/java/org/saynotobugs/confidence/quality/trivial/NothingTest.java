package org.saynotobugs.confidence.quality.trivial;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.test.quality.DescribesAs;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.HasDescription;

import static org.saynotobugs.confidence.Assertion.assertThat;


class NothingTest
{

    @Test
    void test()
    {
        assertThat(new Nothing(),
            new AllOf<>(
                new Fails<Object>("abc", new DescribesAs("\"abc\"")),
                new Fails<Object>(123, new DescribesAs("<123>")),
                new Fails<Object>(null, new DescribesAs("<null>")),
                new HasDescription("<nothing>")));
    }

}