package org.saynotobugs.confidence.quality.grammar;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.trivial.Anything;
import org.saynotobugs.confidence.quality.trivial.Nothing;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class HasTest
{
    @Test
    void testValue()
    {
        assertThat(new Has<>(3),
            new AllOf<>(
                new Passes<>(3),
                new Fails<>(4, "had <4>"),
                new Expects("has <3>")));
    }


    @Test
    void testMatch()
    {
        assertThat(new Has<>(new Anything()),
            new AllOf<>(
                new Passes<>("12", 1, new Object()),
                new Expects("has <anything>")));
    }


    @Test
    void testMismatch()
    {
        assertThat(new Has<>(new Nothing()),
            new AllOf<>(
                new Fails<>(1, "had <1>"),
                new Expects("has <nothing>")));
    }
}