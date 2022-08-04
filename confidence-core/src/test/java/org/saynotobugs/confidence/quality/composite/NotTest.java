package org.saynotobugs.confidence.quality.composite;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.trivial.Anything;
import org.saynotobugs.confidence.quality.trivial.Nothing;
import org.saynotobugs.confidence.test.quality.DescribesAs;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


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