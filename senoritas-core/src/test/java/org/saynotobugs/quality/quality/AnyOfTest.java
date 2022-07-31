package org.saynotobugs.quality.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.AllOf;
import org.saynotobugs.quality.quality.AnyOf;
import org.saynotobugs.quality.quality.EqualTo;
import org.saynotobugs.quality.test.quality.Expects;
import org.saynotobugs.quality.test.quality.Fails;
import org.saynotobugs.quality.test.quality.Passes;

import static org.saynotobugs.quality.Assertion.assertThat;


class AnyOfTest
{
    @Test
    void test()
    {
        assertThat(new AnyOf<>(1, 2, 3),
            new AllOf<>(
                new Passes<>(1, 2, 3),
                new Fails<>(0, "<0> neither <1> nor \n  <2> nor \n  <3>"),
                new Fails<>(4, "<4> neither <1> nor \n  <2> nor \n  <3>"),
                new Expects("<1> or <2> or <3>")
            ));
    }


    @Test
    void testMatchers()
    {
        assertThat(new AnyOf<>(new EqualTo<>(1), new EqualTo<>(2), new EqualTo<>(3)),
            new AllOf<>(
                new Passes<>(1, 2, 3),
                new Fails<>(0, "<0> neither <1> nor \n  <2> nor \n  <3>"),
                new Fails<>(4, "<4> neither <1> nor \n  <2> nor \n  <3>"),
                new Expects("<1> or <2> or <3>")
            ));
    }
}