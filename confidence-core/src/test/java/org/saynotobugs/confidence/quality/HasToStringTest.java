package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.DescribesAs;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class HasToStringTest
{

    @Test
    void test()
    {
        assertThat(new HasToString("123"),
            new AllOf<>(
                new Passes<>("123", 123),
                new Fails<>("1234", new DescribesAs("had toString() \"1234\"")),
                new Expects("has toString() \"123\"")));
    }

}