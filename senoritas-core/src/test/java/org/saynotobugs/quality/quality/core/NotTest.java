package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.DescribesAs;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


class NotTest
{

    @Test
    void test()
    {
        assertThat(new Not<>("123"),
            new AllOf<>(
                new Fails<>("123", new DescribesAs("\"123\" ( \"123\" )")),
                new Expects("not ( \"123\" )")));

        assertThat(new Not<>(new Anything()),
            new AllOf<>(
                new Fails<>("123", new DescribesAs("\"123\" ( <anything> )")),
                new Expects("not ( <anything> )")));

        assertThat(new Not<>(new Nothing()),
            new AllOf<>(
                new Passes<>("123"),
                new Expects("not ( <nothing> )")));
    }

}