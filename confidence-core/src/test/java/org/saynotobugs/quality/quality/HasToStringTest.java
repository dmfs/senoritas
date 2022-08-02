package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.HasToString;
import org.saynotobugs.quality.test.quality.DescribesAs;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


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