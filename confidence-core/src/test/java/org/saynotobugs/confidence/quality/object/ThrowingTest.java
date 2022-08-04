package org.saynotobugs.confidence.quality.object;

import org.junit.jupiter.api.Test;
import org.saynotobugs.confidence.quality.composite.AllOf;
import org.saynotobugs.confidence.quality.object.Throwing;
import org.saynotobugs.confidence.quality.trivial.Anything;
import org.saynotobugs.confidence.quality.trivial.Nothing;
import org.saynotobugs.confidence.test.quality.DescribesAs;
import org.saynotobugs.confidence.test.quality.Expects;
import org.saynotobugs.confidence.test.quality.Fails;
import org.saynotobugs.confidence.test.quality.Passes;

import java.util.NoSuchElementException;

import static org.saynotobugs.confidence.Assertion.assertThat;


class ThrowingTest
{

    @Test
    void test()
    {
        assertThat(new Throwing(new Anything()),
            new AllOf<>(
                new Passes<>((Throwing.Breakable) () -> {throw new NoSuchElementException();}),
                new Fails<>(() -> {}, new DescribesAs("did not throw <anything>")),
                new Expects(new DescribesAs("throwing <anything>"))
            ));
    }


    @Test
    void testNothing()
    {
        assertThat(new Throwing(new Nothing()),
            new AllOf<>(
                new Fails<>(() -> {throw new NoSuchElementException();}, new DescribesAs("threw <java.util.NoSuchElementException>")),
                new Fails<>(() -> {}, new DescribesAs("did not throw <nothing>")),
                new Expects(new DescribesAs("throwing <nothing>"))
            ));
    }


    @Test
    void testClass()
    {
        assertThat(new Throwing(NoSuchElementException.class),
            new AllOf<>(
                new Passes<>((Throwing.Breakable) () -> {throw new NoSuchElementException();}),
                new Fails<>(() -> {}, new DescribesAs("did not throw instance of <class java.util.NoSuchElementException>")),
                new Expects(new DescribesAs("throwing instance of <class java.util.NoSuchElementException>"))
            ));
    }
}