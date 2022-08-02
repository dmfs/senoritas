package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.Anything;
import org.saynotobugs.quality.quality.Not;
import org.saynotobugs.quality.quality.Nothing;
import org.saynotobugs.quality.test.quality.DescribesAs;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

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