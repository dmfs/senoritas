package org.saynotobugs.quality.quality.core;

import org.junit.jupiter.api.Test;
import org.saynotobugs.quality.quality.test.DescribesAs;
import org.saynotobugs.quality.quality.test.Expects;
import org.saynotobugs.quality.quality.test.Fails;
import org.saynotobugs.quality.quality.test.Passes;

import java.util.NoSuchElementException;

import static org.saynotobugs.quality.Assertion.assertThat;


class ThrowingTest
{

    @Test
    void test()
    {
        assertThat(new Throwing(new Anything()),
            new AllOf<>(
                new Passes<>((Throwing.Breakable) () -> {throw new NoSuchElementException();}),
                new Fails<>(() -> {}, new DescribesAs("did not throw <anything>")),
                new Expects(new DescribesAs("throws <anything>"))
            ));
    }


    @Test
    void testNothing()
    {
        assertThat(new Throwing(new Nothing()),
            new AllOf<>(
                new Fails<>(() -> {throw new NoSuchElementException();}, new DescribesAs("threw was something")),
                new Fails<>(() -> {}, new DescribesAs("did not throw <nothing>")),
                new Expects(new DescribesAs("throws <nothing>"))
            ));
    }


    @Test
    void testClass()
    {
        assertThat(new Throwing(NoSuchElementException.class),
            new AllOf<>(
                new Passes<>((Throwing.Breakable) () -> {throw new NoSuchElementException();}),
                new Fails<>(() -> {}, new DescribesAs("did not throw instance of <class java.util.NoSuchElementException>")),
                new Expects(new DescribesAs("throws instance of <class java.util.NoSuchElementException>"))
            ));
    }
}