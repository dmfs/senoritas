package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static java.util.Arrays.asList;
import static org.saynotobugs.confidence.Assertion.assertThat;


class IteratesInAnyOrderTest
{
    @Test
    void test()
    {
        assertThat(new IteratesInAnyOrder<>(1, 2, 3),
            new AllOf<>(
                new Passes<>(asList(1, 2, 3), asList(1, 3, 2), asList(2, 1, 3), asList(2, 3, 1), asList(3, 1, 2), asList(3, 2, 1)),
                new Fails<>(asList(0, 1, 2, 3), "contained also [<0>],did not contain []"),
                new Fails<>(asList(1, 2), "contained also [],did not contain [<3>]"),
                new Fails<>(asList(-1, 0, 1, 2), "contained also [<-1>,\n    <0>],did not contain [<3>]"),
                new Expects("contains in any order [<1>,\n  <2>,\n  <3>]")
            ));
    }


    @Test
    void testMatchers()
    {
        assertThat(new IteratesInAnyOrder<>(new EqualTo<>(1), new EqualTo<>(2), new EqualTo<>(3)),
            new AllOf<>(
                new Passes<>(asList(1, 2, 3), asList(1, 3, 2), asList(2, 1, 3), asList(2, 3, 1), asList(3, 1, 2), asList(3, 2, 1)),
                new Fails<>(asList(0, 1, 2, 3), "contained also [<0>],did not contain []"),
                new Fails<>(asList(1, 2), "contained also [],did not contain [<3>]"),
                new Fails<>(asList(-1, 0, 1, 2), "contained also [<-1>,\n    <0>],did not contain [<3>]"),
                new Expects("contains in any order [<1>,\n  <2>,\n  <3>]")
            ));
    }
}