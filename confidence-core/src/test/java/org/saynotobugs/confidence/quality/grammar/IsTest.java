package org.saynotobugs.confidence.quality.grammar;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.trivial.Anything;
import org.saynotobugs.confidence.quality.trivial.Nothing;
import org.saynotobugs.confidence.test.quality.Description;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import static org.saynotobugs.confidence.Assertion.assertThat;


class IsTest
{
    @Test
    void testValue()
    {
        assertThat(new Is<>(3),
            new AllOf<>(
                new Passes<>(3),
                new Fails<>(4, "was <4>"),
                new Has<>(new Description("is <3>"))));
    }


    @Test
    void testMatch()
    {
        assertThat(new Is<>(new Anything()),
            new AllOf<>(
                new Passes<>("12", 1, new Object()),
                new Has<>(new Description("is <anything>"))));
    }


    @Test
    void testMismatch()
    {
        assertThat(new Is<>(new Nothing()),
            new AllOf<>(
                new Fails<>(1, "was <1>"),
                new Has<>(new Description("is <nothing>"))));
    }
}