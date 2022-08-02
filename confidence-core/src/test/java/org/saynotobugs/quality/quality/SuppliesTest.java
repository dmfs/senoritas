package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.Supplies;
import org.saynotobugs.quality.test.quality.DescribesAs;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Passes;
import org.saynotobugs.quality.test.quality.Fails;

import static org.saynotobugs.quality.Assertion.assertThat;


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