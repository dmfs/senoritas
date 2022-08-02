package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.DescribesAs;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Passes;
import org.saynotobugs.confidence.test.quality.Fails;

import static org.saynotobugs.confidence.Assertion.assertThat;


class SuppliesTest
{

    @Test
    void test()
    {
        assertThat(new Supplies<>(123), new AllOf<>(
            new Passes<>(() -> 123),
            new Fails<>(() -> null, new DescribesAs("supplied value <null>")),
            new Fails<>(() -> 1234, new DescribesAs("supplied value <1234>")),
            new Expects("supplies value <123>")
        ));
    }

}