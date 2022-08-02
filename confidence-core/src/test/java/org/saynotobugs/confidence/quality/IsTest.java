package org.saynotobugs.confidence.quality;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.test.quality.Expects;
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
                new Fails<>(4, "<4>"),
                new Expects("<3>")));
    }


    @Test
    void testMatch()
    {
        assertThat(new Is<>(new Anything()),
            new AllOf<>(
                new Passes<>("12", 1, new Object()),
                new Expects("<anything>")));
    }


    @Test
    void testMismatch()
    {
        assertThat(new Is<>(new Nothing()),
            new AllOf<>(
                new Fails<>(1, "was something"),
                new Expects("<nothing>")));
    }
}